package com.napoleon.bookingapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    public String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    public String email;
}