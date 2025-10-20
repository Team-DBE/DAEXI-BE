package com.example.daexi.domain.party.presentation.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyListResponseDto {
    private Long partyId;

    private String partyName;

    private String startingPoint;

    private String endingPoint;
}