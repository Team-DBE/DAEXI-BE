package com.example.daexi.domain.party.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyDeleteRequestDto {

    @JsonProperty("party_id")
    private Long partyId;

    @NotBlank
    private String partyHost;
}
