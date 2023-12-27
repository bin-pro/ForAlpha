package com.example.legendfive.save.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenResponse {
    private String access_token;
    private String scope;
    private String token_type;
    private String expires_in;
}
