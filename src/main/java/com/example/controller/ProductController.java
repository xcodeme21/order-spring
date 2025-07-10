package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.Product;
import com.example.model.ItemsProductResponse;
import com.example.model.ProductResponse;
import com.example.service.ProductService;
import com.example.service.TokenService;
import com.example.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
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

    @Autowired
    private AuthUtil authUtil;

    @GetMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ItemsProductResponse> getProduct(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "direction", required = false, defaultValue = "next") String direction) {

        authUtil.getUserFromAuth(authHeader);

        if (limit <= 0) limit = 10;
        if (limit > 100) limit = 100;

        List<Product> products;
        if (cursor == null) {
            products = productService.getFirstPage(limit);
        } else if ("previous".equalsIgnoreCase(direction)) {
            products = productService.getPreviousPage(cursor, limit);
        } else {
            products = productService.getNextPage(cursor, limit);
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

        Integer newPrevious = null;
        Integer newNext = null;

        if (!products.isEmpty()) {
            newPrevious = Math.toIntExact(products.get(0).getId());
            newNext = Math.toIntExact(products.get(products.size() - 1).getId());
        }

        ItemsProductResponse items = ItemsProductResponse.builder()
                .items(responseList)
                .previous(newPrevious)
                .next(newNext)
                .build();

        return responseHelper.ok(items, "Successfully get products");
    }

    @GetMapping(value = "/api/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<ProductResponse> getProductById(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String authHeader) {
        authUtil.getUserFromAuth(authHeader);

        Product product = productService.getById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .product_name(product.getProductName())
                .product_description(product.getProductDescription())
                .price(product.getPrice())
                .created_at(product.getCreatedAt())
                .updated_at(product.getUpdatedAt())
                .build();

        return responseHelper.ok(response, "Successfully get data product");
    }
}
