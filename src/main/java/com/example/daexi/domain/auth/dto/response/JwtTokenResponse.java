package com.example.daexi.domain.auth.dto.response;

import lombok.Builder;

@Builder
public class JwtTokenResponse {
    private String accessToken;

    private String refreshToken;
}
