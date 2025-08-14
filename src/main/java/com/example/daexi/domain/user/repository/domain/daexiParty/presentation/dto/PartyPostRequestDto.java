package com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NotBlank
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyPostRequestDto {

    @NotBlank(message = "파티 이름은 필수 입력값입니다.")
    private String partyName;

    private String partyPassword;

    @NotBlank
    private String startingPoint;

    @NotBlank
    private String endingPoint;
}