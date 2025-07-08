package com.example.service;

import com.example.entity.User;
import com.example.entity.UserToken;
import com.example.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public User getUserByToken(String token) {
        UserToken userToken = userTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token"));

        if (System.currentTimeMillis() > userToken.getExpiredAt()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
        }

        return userToken.getUser();
    }
}
