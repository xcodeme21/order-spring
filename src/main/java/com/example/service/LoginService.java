package com.example.service;

import com.example.entity.User;
import com.example.entity.UserToken;
import com.example.model.LoginRequest;
import com.example.model.UserTokenResponse;
import com.example.repository.UserRepository;
import com.example.repository.UserTokenRepository;
import com.example.utils.BCrypt;
import com.example.utils.TimeUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;
import java.util.UUID;

@Service
public class LoginService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Autowired
    private Validator validator;
    
    @Transactional
    public UserTokenResponse login (LoginRequest request) {
        System.out.println("Login request progress validation");
        Set<ConstraintViolation<LoginRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        System.out.println(request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password wrong"));
        System.out.println("user passed");

        if(BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            UserToken token = new UserToken();
            token.setToken(UUID.randomUUID().toString());
            token.setExpiredAt(TimeUtil.expiredTime());
            token.setUser(user);

            System.out.println("Saving token...");
            userTokenRepository.save(token);
            System.out.println("Token saved.");

            return UserTokenResponse.builder()
                    .token(token.getToken())
                    .expired_at(Instant.ofEpochMilli(token.getExpiredAt())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
                    )
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password wrong");
        }
    }
    
    
}
