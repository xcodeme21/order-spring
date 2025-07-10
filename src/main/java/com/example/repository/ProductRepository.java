package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY p.id ASC")
    List<Product> findFirstPage(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.id > :id ORDER BY p.id ASC")
    List<Product> findNextPage(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.id < :id ORDER BY p.id DESC")
    List<Product> findPreviousPage(@Param("id") Long id, Pageable pageable);

}
