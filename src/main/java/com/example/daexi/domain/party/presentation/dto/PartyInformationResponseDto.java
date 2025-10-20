package com.example.daexi.domain.party.presentation.dto;

import com.example.daexi.domain.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PartyInformationResponseDto {
    private User partyHost;

    private String partyName;

    private String accountNumber;

    private LocalDateTime createdAt;

    private LocalDateTime arriveAt;

    private String startingLongitude;

    private String startingLatitude;

    private String endingLongitude;

    private String endingLatitude;

    private List<User> userList;
}
