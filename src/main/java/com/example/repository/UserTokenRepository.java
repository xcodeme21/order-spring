package com.example.repository;

import com.example.entity.UserToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    @Modifying
    @Transactional
    void deleteByUserId(Long userId);

    Optional<UserToken> findByToken(String token);
}
