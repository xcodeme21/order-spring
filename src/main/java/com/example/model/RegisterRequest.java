package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Name is required and cannot be empty.")
    @Size(min = 4, max = 100, message = "Name must be between 4 and 100 characters.")
    private String name;

    @NotBlank(message = "Password is required and cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    @NotBlank(message = "Email is required and cannot be empty.")
    @Size(min = 10, max = 100, message = "Email must be between 10 and 100 characters.")
    private String email;
}
