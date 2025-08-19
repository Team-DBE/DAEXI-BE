package com.example.daexi.domain.room.service;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.domain.party.exception.PartyNotFoundException;
import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.presentation.dto.RoomRequestDto;
import com.example.daexi.domain.room.presentation.dto.RoomResponseDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.global.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.domain.room.entity.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;

    private final UserRepository userRepository;
    private final PartyRepository partyRepository;

    public Room getUserById(RoomRequestDto roomRequestDto, Long userId, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!roomRequestDto.getPassword().equals(party.getPartyPassword())
                && party.getPartyPassword() != null) {
            return null;
        }

        Room room = roomRepository.findByUserAndParty(user, party);
        return roomRepository.save(room);
    }

    public RoomResponseDto joinParty(Room room) {

        roomRepository.save(room);

        return RoomResponseDto.builder()
                .userNumber(room.getUser().getUserNumber())
                .accountNumber(room.getUser().getAccountNumber())
                .username(room.getUser().getUserName())
                .userId(room.getUser().getId())
                .build();

    }
}
