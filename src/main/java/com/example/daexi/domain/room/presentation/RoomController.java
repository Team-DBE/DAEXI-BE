package com.example.daexi.domain.room.presentation;

import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.presentation.dto.RoomInformationResponseDto;
import com.example.daexi.domain.room.presentation.dto.RoomJoinRequestDto;
import com.example.daexi.domain.room.presentation.dto.RoomJoinResponseDto;
import com.example.daexi.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/party/{party_id}/information")
    public ResponseEntity<RoomInformationResponseDto> informationRoom(@PathVariable Long party_id) {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(roomService.getRoomInformation(party_id));
    }

    @PostMapping("/party/{party_id}/join")
    public ResponseEntity<RoomJoinResponseDto> partyJoin(
            @RequestBody RoomJoinRequestDto roomJoinRequestDto,
            @PathVariable Long party_id) {
        try {
            Room room = roomService.getUserById(roomJoinRequestDto, roomJoinRequestDto.getUserId(), party_id);
            RoomJoinResponseDto responseDto = roomService.joinParty(room);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(responseDto);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
