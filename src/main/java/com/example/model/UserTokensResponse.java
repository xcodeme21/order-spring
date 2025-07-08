package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class UserTokensResponse {
    private String token;
    private Integer user_id;
    private BigInteger expired_at;
}
