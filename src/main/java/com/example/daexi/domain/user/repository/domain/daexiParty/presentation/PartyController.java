package com.example.daexi.domain.user.repository.domain.daexiParty.presentation;

import com.example.daexi.domain.user.repository.domain.daexiParty.entity.Party;
import com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto.PartyDeleteRequestDto;
import com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto.PartyInformationResponseDto;
import com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto.PartyListResponseDto;
import com.example.daexi.domain.user.repository.domain.daexiParty.presentation.dto.PartyPostRequestDto;
import com.example.daexi.domain.user.repository.domain.daexiParty.service.PartyService;
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
                .header("Content-Type", "application/json")
                .body(partyService.partyList());
    }

    @PostMapping("/party/post")
    public ResponseEntity<Party> postParty(@RequestBody @Valid PartyPostRequestDto partyPostRequestDto, Principal principal) {
        partyService.postParty(partyPostRequestDto, principal);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .build();
    }

    @DeleteMapping("/party/delete")
    public ResponseEntity<Void> deleteParty(@RequestBody @Valid PartyDeleteRequestDto partydeleteRequestDto, Principal principal) {
        partyService.deleteParty(partydeleteRequestDto, principal);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Content-Type", "application/json")
                .build();
    }

    @GetMapping("/party/{party_id}/information")
    public ResponseEntity<PartyInformationResponseDto> getPartyInformation(@PathVariable Long partyId) {
        return ResponseEntity.ok()
                .header("Content-Type", "applilcation/json")
                .body(partyService.informationParty(partyId));
    }

    @PutMapping("/party/{party_id}/retouch")
    public ResponseEntity<PartyPostRequestDto> retouchParty(@PathVariable Long partyId, @RequestBody @Valid PartyPostRequestDto partyPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(partyService.retouchParty(partyId, partyPostRequestDto));
    }

}
