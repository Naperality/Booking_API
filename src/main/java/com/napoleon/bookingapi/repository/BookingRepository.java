package com.napoleon.bookingapi.repository;

import com.napoleon.bookingapi.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    boolean existsByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
        LocalDateTime startTime,
        LocalDateTime endTime
    );
}