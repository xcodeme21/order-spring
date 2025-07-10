package com.example.repository;

import com.example.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findByUserId(Long userId);

    Optional<Cart> findByIdAndUserId(Long id, Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.id = :id AND c.user.id = :userId")
    void deleteByIdAndUserId(Long id, Long userId);
}
