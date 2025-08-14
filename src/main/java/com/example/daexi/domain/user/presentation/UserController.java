package com.example.daexi.domain.user.presentation;

import com.example.daexi.domain.user.presentation.dto.UserJoinPartyRequestDto;
import com.example.daexi.domain.user.presentation.dto.UserJoinPartyResponseDto;
import com.example.daexi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/party/{party_id}/join")
    public ResponseEntity<UserJoinPartyResponseDto> partyJoin(@RequestBody UserJoinPartyRequestDto userJoinPartyRequestDto, @PathVariable Long partyId, UserJoinPartyResponseDto userJoinPartyResponseDto) {
        userService.joinParty(
                userJoinPartyResponseDto, userService
                        .getUserById(
                                userJoinPartyRequestDto
                                        .getUserId(), partyId));
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(userJoinPartyResponseDto);
    }
}
