package com.example.daexi.domain.room.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RoomInformationResponseDto {
    private String partyHost;

    private String partyName;

    private String accountNumber;

    private LocalDateTime createdAt;

    private String startingLongitude;

    private String startingLatitude;

    private String endingLongitude;

    private String endingLatitude;
}
