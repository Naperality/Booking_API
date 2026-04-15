package com.napoleon.bookingapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Booking() {}

    public Booking(LocalDateTime startTime, LocalDateTime endTime, User user) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }

    public Long getId() { return id; }

    public LocalDateTime getStartTime() { return startTime; }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }

    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}