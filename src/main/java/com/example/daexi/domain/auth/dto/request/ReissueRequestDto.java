package com.example.daexi.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class ReissueRequestDto {
    private String accountId;

    private String refreshToken;
}
