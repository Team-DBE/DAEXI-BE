package com.example.daexi.domain.party.service;

import com.example.daexi.domain.auth.exception.NoAccountNumberException;
import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.domain.party.presentation.dto.PartyInformationResponseDto;
import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.room.entity.repository.RoomRepository;
import com.example.daexi.domain.user.entity.User;
import com.example.daexi.domain.user.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import com.example.daexi.domain.party.exception.PartyNotFoundException;
import com.example.daexi.domain.party.presentation.dto.PartyPostRequestDto;
import com.example.daexi.global.security.auth.CustomUserDetails;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PartyService {

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public List<Party> listParty(int pageNum, int pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);

        return partyRepository.findAllBy(page);
    }

    public PartyInformationResponseDto getPartyInformation(Long partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(() -> new PartyNotFoundException("Party not found"));

        List<Room> rooms = roomRepository.findByParty(party);
        List<User> users = List.of();

        for(int i = 0;i<rooms.size();i++){
            users.add(rooms.get(i).getUser());
        }

        return PartyInformationResponseDto.builder()
                .accountNumber(party.getPartyhost().getAccountNumber())
                .partyHost(party.getPartyhost())
                .partyName(party.getPartyName())
                .createdAt(party.getCreatedAt())
                .arriveAt(party.getArriveAt())
                .startingLatitude(party.getStartingLatitude())
                .startingLongitude(party.getStartingLongitude())
                .endingLatitude(party.getEndingLatitude())
                .endingLongitude(party.getEndingLongitude())
                .userList(users)
                .build();
    }

    @Transactional
    public Party postParty(@Valid PartyPostRequestDto partyPostRequestDto, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String accountId = customUserDetails.getAccountId();

        User user = userRepository.findByAccountId(accountId).orElseThrow(() -> new UserNotFoundException());
        if(user.getAccountNumber() == "" || user.getAccountNumber() == null){
            throw new NoAccountNumberException();
        }

        Party party = Party.builder()
                .partyName(partyPostRequestDto.getPartyName())
                .partyPassword(partyPostRequestDto.getPartyPassword())
                .arriveAt(partyPostRequestDto.getArriveAt())
                .startingLatitude(partyPostRequestDto.getStartingLatitude())
                .startingLongitude(partyPostRequestDto.getStartingLongitude())
                .endingLatitude(partyPostRequestDto.getEndingLatitude())
                .endingLongitude(partyPostRequestDto.getEndingLongitude())
                .createdAt(LocalDateTime.now())
                .partyhost(user)
                .build();

        partyRepository.save(party);
        return party;
    }

    public void deleteParty(Long partyId, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String accountId = customUserDetails.getAccountId();
        User user = userRepository.findByAccountId(accountId).orElseThrow(() -> new UserNotFoundException());
        partyRepository.findById(partyId).ifPresent(party -> {
            if (user.equals(party.getPartyhost())) {
                partyRepository.delete(party);
            }
        });
    }

    @Transactional
    public Party retouchParty(Long partyId, PartyPostRequestDto partyPostRequestDto) {

        Party partyEntity = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        partyEntity = partyEntity.updateParty(
                partyPostRequestDto.getPartyName(),
                partyPostRequestDto.getPartyPassword(),
                partyPostRequestDto.getArriveAt(),
                partyPostRequestDto.getStartingLatitude(),
                partyPostRequestDto.getStartingLongitude(),
                partyPostRequestDto.getEndingLatitude(),
                partyPostRequestDto.getEndingLongitude());

        return partyRepository.save(partyEntity);
    }
}
