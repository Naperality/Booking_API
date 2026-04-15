package com.napoleon.bookingapi.controller;

import com.napoleon.bookingapi.dto.UserRequest;
import com.napoleon.bookingapi.dto.UserResponse;
import com.napoleon.bookingapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return service.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAllUsers();
    }
}