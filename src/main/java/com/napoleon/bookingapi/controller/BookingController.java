package com.napoleon.bookingapi.controller;

import com.napoleon.bookingapi.dto.BookingRequest;
import com.napoleon.bookingapi.model.Booking;
import com.napoleon.bookingapi.model.User;
import com.napoleon.bookingapi.repository.UserRepository;
import com.napoleon.bookingapi.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService,
                             UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    // CREATE BOOKING (USER)
    @PostMapping
    public Booking create(@RequestBody BookingRequest request,
                          Authentication auth) {

        String email = auth.getName();

        return bookingService.createBooking(email, request);
    }

    // GET MY BOOKINGS (USER)
    @GetMapping("/me")
    public List<Booking> myBookings(Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return bookingService.getMyBookings(user.getId());
    }

    // ADMIN VIEW ALL BOOKINGS
    @GetMapping
    public List<Booking> allBookings() {
        return bookingService.getAllBookings();
    }
}