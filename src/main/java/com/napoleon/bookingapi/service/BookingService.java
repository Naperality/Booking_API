package com.napoleon.bookingapi.service;

import com.napoleon.bookingapi.dto.BookingRequest;
import com.napoleon.bookingapi.model.Booking;
import com.napoleon.bookingapi.model.User;
import com.napoleon.bookingapi.repository.BookingRepository;
import com.napoleon.bookingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(String email, BookingRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ❗ prevent double booking (simple version)
        if (bookingRepository
            .existsByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                request.endTime,
                request.startTime
        )) {
            throw new RuntimeException("Time slot overlaps with existing booking");
        }

        Booking booking = new Booking(
                request.startTime,
                request.endTime,
                user
        );

        return bookingRepository.save(booking);
    }

    public List<Booking> getMyBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}