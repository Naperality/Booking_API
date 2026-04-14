package com.napoleon.bookingapi.controller;

import com.napoleon.bookingapi.model.User;
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
    public User create(@Valid @RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }
}