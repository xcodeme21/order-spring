package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.User;
import com.example.model.UserResponse;
import com.example.service.ProfileService;
import com.example.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResponseHelper responseHelper;

    @GetMapping(value = "/api/profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> profile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        User user = tokenService.getUserByToken(token);
        UserResponse response = profileService.get(user);
        return responseHelper.ok(response, "Successfully get profile");
    }

    @PostMapping(value = "/api/profile/logout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        User user = tokenService.getUserByToken(token);
        System.out.println("Ini adalah data user");
        System.out.println(user);

        profileService.logout(user);
        return responseHelper.ok(null, "Successfully logged out");
    }
}
