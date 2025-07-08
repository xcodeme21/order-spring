package com.example.repository;

import com.example.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    void deleteByUserId(Long userId);
    Optional<UserToken> findByToken(String token);
}
