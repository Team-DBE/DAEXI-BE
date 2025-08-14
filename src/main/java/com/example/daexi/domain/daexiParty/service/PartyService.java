package com.example.daexi.domain.daexiParty.service;

import com.example.daexi.domain.daexiParty.entity.Party;
import com.example.daexi.domain.daexiParty.entity.repository.PartyRepository;
import com.example.daexi.domain.daexiParty.presentation.dto.PartyDeleteRequestDto;
import com.example.daexi.domain.daexiParty.presentation.dto.PartyInformationResponseDto;
import com.example.daexi.domain.daexiParty.presentation.dto.PartyPostRequestDto;
import com.example.daexi.domain.daexiParty.presentation.dto.PartyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;

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

    public Party postParty(PartyPostRequestDto partyPostRequestDto, Principal principal) {
        String username = principal.getName();

        Party party = Party.builder()
                .partyName(partyPostRequestDto.getPartyName())
                .partyPassword(partyPostRequestDto.getPartyPassword())
                .startingPoint(partyPostRequestDto.getStartingPoint())
                .endingPoint(partyPostRequestDto.getEndingPoint())
                .createdAt(LocalDateTime.now())
                .partyHost(username)
                .build();

        partyRepository.save(party);
        return party;
    }

    public void deleteParty(PartyDeleteRequestDto partyDeleteRequestDto, Principal principal) {
        String username = principal.getName();
        if (Party.builder()
                .partyHost(partyDeleteRequestDto.toString())
                .build()
                .getPartyHost()
                .equals(username)) {
            partyRepository.deleteById(partyDeleteRequestDto.getPartyId());
        }
    }

    public PartyInformationResponseDto informationParty(Long partyId) {

        Party partyEntity = partyRepository.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found"));

        PartyInformationResponseDto partyInformationResponseDto = PartyInformationResponseDto.builder()
                .partyId(partyEntity.getPartyId())
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
                .orElseThrow(() -> new RuntimeException("Party not found"));

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
