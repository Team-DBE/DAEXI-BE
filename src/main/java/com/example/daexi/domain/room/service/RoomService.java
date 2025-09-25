package com.example.daexi.domain.room.service;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.domain.room.presentation.dto.RoomInformationResponseDto;
import com.example.daexi.global.exception.PartyNotFoundException;
import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.presentation.dto.RoomJoinRequestDto;
import com.example.daexi.domain.room.presentation.dto.RoomJoinResponseDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserNotFoundException;
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

    public RoomInformationResponseDto getRoomInformation(Long partyId) {
        Room room = roomRepository.findRoomByPartyId(partyId);

        return RoomInformationResponseDto.builder()
                .accountNumber(room.getParty().getAccountId())
                .partyHost(room.getParty().getPartyHost())
                .partyName(room.getParty().getPartyName())
                .createdAt(room.getParty().getCreatedAt())
                .startingLatitude(room.getParty().getStartingLatitude())
                .startingLongitude(room.getParty().getStartingLongitude())
                .endingLatitude(room.getParty().getEndingLatitude())
                .endingLongitude(room.getParty().getEndingLongitude())
                .build();
    }

    public Room getUserById(RoomJoinRequestDto roomJoinRequestDto, Long userId, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!roomJoinRequestDto.getPassword().equals(party.getPartyPassword())
                && party.getPartyPassword() != null) {
            return null;
        }

        Room room = roomRepository.findByUserAndParty(user, party);
        return roomRepository.save(room);
    }

    public RoomJoinResponseDto joinParty(Room room) {

        roomRepository.save(room);

        return RoomJoinResponseDto.builder()
                .accountNumber(room.getParty().getAccountId())
                .username(room.getUser().getUserName())
                .userId(room.getUser().getId())
                .build();

    }
}
