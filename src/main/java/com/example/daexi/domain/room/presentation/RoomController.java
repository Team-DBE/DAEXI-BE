package com.example.daexi.domain.room.presentation;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.room.presentation.dto.RoomJoinRequestDto;
import com.example.daexi.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/party")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/{partyId}/join")
    public ResponseEntity<Party> partyJoin(
            @RequestBody RoomJoinRequestDto roomJoinRequestDto,
            @PathVariable Long partyId,
            Authentication authentication) {
        Party party = roomService.joinParty(roomJoinRequestDto, partyId,authentication);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(party);
    }
}
