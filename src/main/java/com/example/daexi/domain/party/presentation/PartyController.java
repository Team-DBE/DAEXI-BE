package com.example.daexi.domain.party.presentation;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.party.presentation.dto.PartyPostRequestDto;
import com.example.daexi.domain.party.service.PartyService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/party/list")
    public ResponseEntity<Page<Party>> getPartyList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Party> listParty = partyService.listParty(page, size, sortBy);
        return ResponseEntity.ok(listParty);
    }

    @PostMapping("/party/post")
    public ResponseEntity<Party> postParty(@RequestBody @Valid PartyPostRequestDto partyPostRequestDto, Authentication authentication) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(partyService.postParty(partyPostRequestDto, authentication));
    }

    @DeleteMapping("/party/{party_id}/delete")
    public ResponseEntity<Void> deleteParty(@PathVariable Long party_id, Authentication authentication) {
        partyService.deleteParty(party_id, authentication);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Content-Type", "application/json")
                .build();
    }

    @PutMapping("/party/{party_id}/retouch")
    public ResponseEntity<Party> retouchParty(@PathVariable Long party_id, @RequestBody @Valid PartyPostRequestDto partyPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(partyService.retouchParty(party_id, partyPostRequestDto));
    }

}
