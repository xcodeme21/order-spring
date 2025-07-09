package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getFirstPage(int limit) {
        return productRepository.findFirstPage(PageRequest.of(0, limit));
    }

    public List<Product> getNextPage(Long id, int limit) {
        return productRepository.findNextPage(id, PageRequest.of(0, limit));
    }

    public List<Product> getPreviousPage(Long id, int limit) {
        List<Product> list = productRepository.findPreviousPage(id, PageRequest.of(0, limit));
        Collections.reverse(list);
        return list;
    }
}
