package com.example.service;

import com.example.entity.Cart;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.model.AddToCartRequest;
import com.example.model.CartResponse;
import com.example.model.LoginRequest;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public CartResponse addToCart(AddToCartRequest request) {
        Set<ConstraintViolation<AddToCartRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        System.out.println("berhasil validasi");

        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("berhasil get user");

        Product product = productRepository.findById(request.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        System.out.println("berhasil get product");

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQty(request.getQty());
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());

        Cart savedCart = cartRepository.save(cart);
        System.out.println("berhasil atc");

        return CartResponse.builder()
                .id(savedCart.getId())
                .user_id(savedCart.getUser().getId())
                .product_id(savedCart.getProduct().getId())
                .qty(savedCart.getQty())
                .created_at(savedCart.getCreatedAt())
                .updated_at(savedCart.getUpdatedAt())
                .build();
    }
}
