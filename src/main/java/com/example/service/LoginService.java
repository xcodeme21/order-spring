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
        Set<ConstraintViolation<LoginRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password wrong"));

        if(BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            userTokenRepository.deleteByUserId(user.getId());

            UserToken token = new UserToken();
            token.setToken(UUID.randomUUID().toString());
            token.setExpiredAt(TimeUtil.expiredTime());
            token.setUser(user);

            userTokenRepository.save(token);

            return UserTokenResponse.builder()
                    .token(token.getToken())
                    .expired_at(Instant.ofEpochMilli(token.getExpiredAt())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
                    )
                    .user_id(token.getUser().getId())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password wrong");
        }
    }
    
    
}
