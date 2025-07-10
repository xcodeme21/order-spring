package com.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    @NotNull(message = "User is required and cannot be empty.")
    private Long user_id;

    @NotNull(message = "Product is required and cannot be empty.")
    private Long product_id;

    @NotNull(message = "Qty is required and cannot be empty.")
    private Integer qty;
}
