package com.example.daexi.domain.daexiParty.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyDeleteRequestDto {

    @NotBlank
    private Long partyId;

    @NotBlank
    private String partyName;
}
