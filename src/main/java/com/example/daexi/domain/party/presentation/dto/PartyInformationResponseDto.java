package com.example.daexi.domain.party.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyInformationResponseDto {

    private String partyName;

    private String partyHost;

    private LocalDateTime createdAt;

    private String startingPoint;

    private String endingPoint;

}
