package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.model.ProductResponse;
import com.example.service.ProductService;
import com.example.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseHelper responseHelper;

    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "/api/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<ProductResponse>> getProduct(@RequestHeader("Authorization") String authHeader,
                                                   @RequestParam(value = "next", required = false) Long nextId,
                                                   @RequestParam(value = "previous", required = false) Long previousId,
                                                   @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header must be provided and start with 'Bearer '");
        }

        String token = authHeader.replace("Bearer", "").trim();
        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token cannot be empty");
        }

        tokenService.getUserByToken(token);

        if (limit > 100) limit = 100;
        if (limit <= 0) limit = 10;

        List<Product> products;
        if (nextId != null) {
            products = productService.getNextPage(nextId, limit);
        } else if (previousId != null) {
            products = productService.getPreviousPage(previousId, limit);
        } else {
            products = productService.getFirstPage(limit);
        }

        List<ProductResponse> responseList = products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .product_name(product.getProductName())
                        .product_description(product.getProductDescription())
                        .price(product.getPrice())
                        .created_at(product.getCreatedAt())
                        .updated_at(product.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());

        return responseHelper.ok(responseList, "Successfully get data products");
    }
}
