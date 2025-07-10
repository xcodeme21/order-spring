package com.example.utils;

import com.example.entity.User;
import com.example.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthUtil {

    private final TokenService tokenService;

    @Autowired
    public AuthUtil(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public User getUserFromAuth(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        return tokenService.getUserByToken(token);
    }
}
