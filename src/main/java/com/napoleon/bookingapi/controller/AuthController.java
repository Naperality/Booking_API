package com.napoleon.bookingapi.controller;

import com.napoleon.bookingapi.dto.LoginRequest;
import com.napoleon.bookingapi.dto.RegisterRequest;
import com.napoleon.bookingapi.dto.AuthResponse;
import com.napoleon.bookingapi.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // 🔐 REGISTER
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}