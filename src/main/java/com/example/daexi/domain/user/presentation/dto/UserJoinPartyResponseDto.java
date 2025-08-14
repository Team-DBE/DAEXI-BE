package com.example.daexi.domain.user.presentation.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinPartyResponseDto {

    private final Long userId;

    private final String username;

    private final String accountNumber;

    private final String userNumber;

}
