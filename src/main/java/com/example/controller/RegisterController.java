package com.example.controller;

import com.example.entity.User;
import com.example.model.RegisterRequest;
import com.example.model.UserResponse;
import com.example.advice.WebResponse;
import com.example.service.RegisterService;
import com.example.advice.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ResponseHelper responseHelper;

    @PostMapping(path = "/api/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = registerService.register(registerRequest);

        UserResponse response = UserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();

        return responseHelper.ok(response, "Registration successful");
    }
}
