package com.example.daexi.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String accountId;

    private String password;

    private String userName;

    private String accountNumber;

    private int userNumber;
}
