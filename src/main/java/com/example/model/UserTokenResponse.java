package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserTokenResponse {
    private String token;
    private Long user_id;
    private LocalDateTime expired_at;
}
