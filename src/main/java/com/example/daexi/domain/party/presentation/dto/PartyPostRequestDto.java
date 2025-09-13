package com.example.daexi.domain.party.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class PartyPostRequestDto {

    @NotBlank(message = "party_name don't null")
    private String partyName;

    private String partyPassword;

    private String startingLongitude;

    private String startingLatitude;

    private String endingLongitude;

    private String endingLatitude;

    @NotBlank
    private String accountId;
}