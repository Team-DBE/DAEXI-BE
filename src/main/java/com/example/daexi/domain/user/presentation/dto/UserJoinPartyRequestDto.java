package com.example.daexi.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserJoinPartyRequestDto {

    private final Long partyId;

    private final Long userId;
}
