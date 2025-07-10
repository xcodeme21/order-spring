package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.Cart;
import com.example.entity.User;
import com.example.model.AddToCartRequest;
import com.example.model.CartResponse;
import com.example.service.CartService;
import com.example.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResponseHelper responseHelper;


    @GetMapping(value = "/api/carts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<CartResponse>> getCart(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        User user = tokenService.getUserByToken(token);

        List<Cart> carts = cartService.getCartByUserId(user.getId());

        List<CartResponse> cartResponses = carts.stream()
                .map(cart -> CartResponse.builder()
                        .id(cart.getId())
                        .user_id(cart.getUser().getId())
                        .product_id(cart.getProduct().getId())
                        .qty(cart.getQty())
                        .created_at(cart.getCreatedAt())
                        .updated_at(cart.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        return responseHelper.ok(cartResponses, "Successfully get carts");
    }

    @PostMapping(value = "/api/carts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<CartResponse> addToCart(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody AddToCartRequest addToCartRequest) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        tokenService.getUserByToken(token);
        CartResponse response = cartService.addToCart(addToCartRequest);
        return responseHelper.ok(response, "Successfully inserted cart");
    }


}
