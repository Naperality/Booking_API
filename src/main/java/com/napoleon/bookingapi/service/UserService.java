package com.napoleon.bookingapi.service;

import com.napoleon.bookingapi.dto.UserRequest;
import com.napoleon.bookingapi.dto.UserResponse;
import com.napoleon.bookingapi.model.User;
import com.napoleon.bookingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);

        User saved = repository.save(user);

        return mapToResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.id = user.getId();
        res.name = user.getName();
        res.email = user.getEmail();
        return res;
    }
}