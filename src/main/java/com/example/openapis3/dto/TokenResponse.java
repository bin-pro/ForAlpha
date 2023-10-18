package com.example.openapis3.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private String access_token;
    private String token_expired;
    private String token_type;
    private Long expires_in;
}
