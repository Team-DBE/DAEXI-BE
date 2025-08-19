package com.example.daexi.domain.party.service;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.domain.party.exception.PartyNotFoundException;
import com.example.daexi.domain.party.presentation.dto.PartyDeleteRequestDto;
import com.example.daexi.domain.party.presentation.dto.PartyInformationResponseDto;
import com.example.daexi.domain.party.presentation.dto.PartyPostRequestDto;
import com.example.daexi.domain.party.presentation.dto.PartyListResponseDto;
import com.example.daexi.global.exception.UserNotFoundException;
import com.example.daexi.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class PartyService {

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    public List<PartyListResponseDto> partyList() {
        List<Party> partyEntities = partyRepository.findAll(Sort.by(Sort.Direction.ASC, "partyId"));

        return partyEntities.stream()
                .map(this::convertTODto)
                .collect(Collectors.toList());
    }

    public PartyListResponseDto convertTODto(Party party) {
        return PartyListResponseDto.builder()
                .partyId(party.getPartyId())
                .partyName(party.getPartyName())
                .partyHost(party.getPartyHost())
                .startingPoint(party.getStartingPoint())
                .endingPoint(party.getEndingPoint())
                .build();
    }

    @Transactional
    public Party postParty(@Valid PartyPostRequestDto partyPostRequestDto, @Valid Principal principal) {

        Party party = Party.builder()
                .partyName(partyPostRequestDto.getPartyName())
                .partyPassword(partyPostRequestDto.getPartyPassword())
                .startingPoint(partyPostRequestDto.getStartingPoint())
                .endingPoint(partyPostRequestDto.getEndingPoint())
                .createdAt(LocalDateTime.now())
                .partyHost(principal.getName())
                .user(userRepository.findById(partyPostRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found")))
                .build();

        partyRepository.save(party);
        return party;
    }

    public void deleteParty(PartyDeleteRequestDto partyDeleteRequestDto, Principal principal) {
        if (partyRepository.findById(partyDeleteRequestDto.getPartyId()).get().getPartyHost().equals(principal.getName())) {
            partyRepository.deleteById(partyDeleteRequestDto.getPartyId());
        }
    }

    public PartyInformationResponseDto informationParty(Long partyId) {

        Party partyEntity = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        PartyInformationResponseDto partyInformationResponseDto = PartyInformationResponseDto.builder()
                .partyName(partyEntity.getPartyName())
                .partyHost(partyEntity.getPartyHost())
                .startingPoint(partyEntity.getStartingPoint())
                .endingPoint(partyEntity.getEndingPoint())
                .createdAt(partyEntity.getCreatedAt())
                .build();

        return partyInformationResponseDto;
    }

    public PartyPostRequestDto retouchParty(Long partyId, PartyPostRequestDto partyPostRequestDto) {

        Party partyEntity = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        partyEntity.setPartyName(partyPostRequestDto.getPartyName());
        partyEntity.setPartyPassword(partyPostRequestDto.getPartyPassword());
        partyEntity.setStartingPoint(partyPostRequestDto.getStartingPoint());
        partyEntity.setEndingPoint(partyPostRequestDto.getEndingPoint());

        Party updateParty = partyRepository.save(partyEntity);

        partyPostRequestDto.builder()
                .partyName(updateParty.getPartyName())
                .partyPassword(updateParty.getPartyPassword())
                .startingPoint(updateParty.getStartingPoint())
                .endingPoint(updateParty.getEndingPoint())
                .build();

        return partyPostRequestDto;
    }



}
