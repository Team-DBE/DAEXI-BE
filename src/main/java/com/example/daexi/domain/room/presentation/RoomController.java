package com.example.daexi.domain.room.presentation;

import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.presentation.dto.RoomRequestDto;
import com.example.daexi.domain.room.presentation.dto.RoomResponseDto;
import com.example.daexi.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/party/{party_id}/join")
    public ResponseEntity<RoomResponseDto> partyJoin(
            @RequestBody RoomRequestDto roomRequestDto,
            @PathVariable Long partyId) {
        try {
            Room room = roomService.getUserById(roomRequestDto, roomRequestDto.getUserId(), partyId);
            RoomResponseDto responseDto = roomService.joinParty(room);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json")
                    .body(responseDto);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
