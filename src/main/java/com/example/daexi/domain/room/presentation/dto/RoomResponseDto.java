package com.example.daexi.domain.room.presentation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomResponseDto {

    private final Long userId;

    private final String username;

    private final String accountNumber;

    private final int userNumber;

}