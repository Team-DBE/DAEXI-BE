package com.example.daexi.global.scheduler;

import com.example.daexi.domain.party.entity.repository.PartyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class PartySchedule {

    @Autowired
    private PartyRepository partyRepository;

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void deleteOldParties() {
        LocalDateTime createdAt = LocalDateTime.now().minusHours(5);
        partyRepository.deleteByCreatedAtBefore(createdAt);
    }
}
