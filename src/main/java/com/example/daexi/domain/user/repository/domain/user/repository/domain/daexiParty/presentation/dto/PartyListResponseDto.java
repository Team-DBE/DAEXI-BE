package com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyListResponseDto {
    private Long partyId;

    private String partyName;

    private String partyHost;

    private String startingPoint;

    private String endingPoint;
}