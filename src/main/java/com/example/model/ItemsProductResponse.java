package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ItemsProductResponse {
    private List<ProductResponse> items;
    private Integer previous;
    private Integer next;
}
