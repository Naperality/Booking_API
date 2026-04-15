package com.napoleon.bookingapi.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {
    @NotNull
    public LocalDateTime startTime;
    @NotNull
    public LocalDateTime endTime;
}