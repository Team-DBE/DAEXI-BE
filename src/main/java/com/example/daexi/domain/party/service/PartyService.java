package com.example.daexi.domain.party.service;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.entity.repository.PartyRepository;
import com.example.daexi.global.exception.PartyNotFoundException;
import com.example.daexi.domain.party.presentation.dto.PartyPostRequestDto;
import com.example.daexi.global.security.auth.CustomUserDetails;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Validated
public class PartyService {

    private final PartyRepository partyRepository;

    public Page<Party> listParty(int pageNum, int pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));

        return partyRepository.findPageBy(page);
    }

    @Transactional
    public Party postParty(@Valid PartyPostRequestDto partyPostRequestDto, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userName = customUserDetails.getUserName();

        Party party = Party.builder()
                .partyName(partyPostRequestDto.getPartyName())
                .partyPassword(partyPostRequestDto.getPartyPassword())
                .startingLatitude(partyPostRequestDto.getStartingLatitude())
                .startingLongitude(partyPostRequestDto.getStartingLongitude())
                .endingLatitude(partyPostRequestDto.getEndingLatitude())
                .endingLongitude(partyPostRequestDto.getEndingLongitude())
                .createdAt(LocalDateTime.now())
                .partyHost(userName)
                .accountId(partyPostRequestDto.getAccountId())
                .build();

        partyRepository.save(party);
        return party;
    }

    public void deleteParty(Long partyId, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String userName = customUserDetails.getUserName();

        partyRepository.findById(partyId).ifPresent(party -> {
            if (party.getPartyHost().equals(userName)) {
                partyRepository.delete(party);
            }
        });
    }

    public Party retouchParty(Long partyId, PartyPostRequestDto partyPostRequestDto) {

        Party partyEntity = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        partyEntity.setPartyName(partyPostRequestDto.getPartyName());
        partyEntity.setPartyPassword(partyPostRequestDto.getPartyPassword());
        partyEntity.setStartingLongitude(partyPostRequestDto.getStartingLongitude());
        partyEntity.setStartingLatitude(partyPostRequestDto.getStartingLatitude());
        partyEntity.setEndingLongitude(partyPostRequestDto.getEndingLongitude());
        partyEntity.setEndingLatitude(partyPostRequestDto.getEndingLatitude());

        return partyRepository.save(partyEntity);
    }
}