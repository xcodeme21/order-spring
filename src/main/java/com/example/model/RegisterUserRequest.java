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
public class RegisterUserRequest {
    @NotBlank
    @Size(min = 4, max = 100)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 15, max = 100)
    private String email;

}
