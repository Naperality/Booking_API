package com.napoleon.bookingapi.service;

import com.napoleon.bookingapi.dto.LoginRequest;
import com.napoleon.bookingapi.dto.RegisterRequest;
import com.napoleon.bookingapi.dto.AuthResponse;
import com.napoleon.bookingapi.model.Role;
import com.napoleon.bookingapi.model.User;
import com.napoleon.bookingapi.repository.UserRepository;
import com.napoleon.bookingapi.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repository,
                       PasswordEncoder encoder,
                       JwtService jwtService) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    // 🔐 REGISTER
    public AuthResponse register(RegisterRequest request) {

        // (optional but recommended)
        if (repository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.name); // ✅ FIXED (important)
        user.setEmail(request.email);
        user.setPassword(encoder.encode(request.password));
        user.setRole(Role.USER);

        repository.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    // 🔐 LOGIN
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}