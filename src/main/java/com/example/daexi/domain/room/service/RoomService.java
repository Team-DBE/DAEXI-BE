package com.example.daexi.domain.room.service;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.domain.party.exception.PartyNotFoundException;
import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.exception.PartyPasswordIncorrectException;
import com.example.daexi.domain.room.presentation.dto.RoomJoinRequestDto;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.domain.room.entity.repository.RoomRepository;
import com.example.daexi.global.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PartyRepository partyRepository;


    @Transactional
    public Party joinParty(RoomJoinRequestDto requestDto, Long partyId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String accountId = userDetails.getAccountId();

        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UserNotFoundException());

        if (requestDto.getPassword().equals(party.getPartyPassword())
                || party.getPartyPassword() != null) {
            roomRepository.save(Room.builder()
                    .party(party)
                    .user(user)
                    .build());
        }
        else
            throw new PartyPasswordIncorrectException();
        return party;
    }
}
