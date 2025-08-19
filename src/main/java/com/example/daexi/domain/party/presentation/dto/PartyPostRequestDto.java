package com.example.daexi.domain.party.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyPostRequestDto {

    @NotBlank(message = "party_name don't null")
    private String partyName;

    private String partyPassword;

    @NotBlank(message = "starting_point don't null")
    private String startingPoint;

    @NotBlank(message = "ending_point don't null")
    private String endingPoint;

    private Long userId;
}