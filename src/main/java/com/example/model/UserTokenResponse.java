package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
public class UserTokenResponse {
    private String token;
    private Integer user_id;
    private LocalDateTime expired_at;
}
