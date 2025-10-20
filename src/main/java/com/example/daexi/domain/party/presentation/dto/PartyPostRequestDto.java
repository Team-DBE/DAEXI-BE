package com.example.daexi.domain.party.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartyPostRequestDto {

    @NotBlank(message = "party_name don't null")
    private String partyName;

    private String partyPassword;

    private LocalDateTime arriveAt;

    private String startingLongitude;

    private String startingLatitude;

    private String endingLongitude;

    private String endingLatitude;
}