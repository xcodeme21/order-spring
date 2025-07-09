package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSeederepository extends JpaRepository<Product, Long> {
}
