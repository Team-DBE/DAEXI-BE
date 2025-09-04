package com.example.daexi.domain.party.presentation;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.presentation.dto.PartyDeleteRequestDto;
import com.example.daexi.domain.party.presentation.dto.PartyInformationResponseDto;
import com.example.daexi.domain.party.presentation.dto.PartyListResponseDto;
import com.example.daexi.domain.party.presentation.dto.PartyPostRequestDto;
import com.example.daexi.domain.party.service.PartyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/party/list")
    public ResponseEntity<List<PartyListResponseDto>> getPartyList() {
        return ResponseEntity.ok()
                .body(partyService.partyList());
    }

    @PostMapping("/party/post")
    public ResponseEntity<Party> postParty(@RequestBody @Valid PartyPostRequestDto partyPostRequestDto, Principal principal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(partyService.postParty(partyPostRequestDto, principal));
    }

    @DeleteMapping("/party/delete")
    public ResponseEntity<Void> deleteParty(@RequestBody @Valid PartyDeleteRequestDto partydeleteRequestDto, @Valid Principal principal) {
        partyService.deleteParty(partydeleteRequestDto, principal);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Content-Type", "application/json")
                .build();
    }

    @GetMapping("/party/{party_id}/information")
    public ResponseEntity<PartyInformationResponseDto> getPartyInformation(@PathVariable Long party_id) {
        return ResponseEntity.ok()
                .body(partyService.informationParty(party_id));
    }

    @PutMapping("/party/{party_id}/retouch")
    public ResponseEntity<PartyPostRequestDto> retouchParty(@PathVariable Long party_id, @RequestBody @Valid PartyPostRequestDto partyPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(partyService.retouchParty(party_id, partyPostRequestDto));
    }

}
