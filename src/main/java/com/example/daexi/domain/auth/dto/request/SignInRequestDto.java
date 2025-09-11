package com.example.daexi.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String accountId;

    private String password;
}