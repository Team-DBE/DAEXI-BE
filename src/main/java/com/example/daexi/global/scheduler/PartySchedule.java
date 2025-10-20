package com.example.daexi.global.scheduler;

import com.example.daexi.domain.party.entity.repository.PartyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PartySchedule {

    private final PartyRepository partyRepository;

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void deleteOldParties() {
        LocalDateTime now = LocalDateTime.now();
        partyRepository.deleteByArriveAtBefore(now);
    }
}
