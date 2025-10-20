package com.example.daexi.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetailResponseDto {
    private String userName;

    private String accountId;

    private String userDetail;

    private String accountNumber;
}
