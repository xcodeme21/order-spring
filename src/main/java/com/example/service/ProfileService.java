package com.example.service;

import com.example.entity.User;
import com.example.model.UserResponse;
import com.example.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserResponse get(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(user.getCreatedAt())
                .updated_at(user.getUpdatedAt())
                .build();
    }

    public void logout(User user) {
        userTokenRepository.deleteByUserId(user.getId());
    }
}
