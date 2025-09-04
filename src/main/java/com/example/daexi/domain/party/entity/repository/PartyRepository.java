package com.example.daexi.domain.party.entity.repository;

import com.example.daexi.domain.party.entity.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Page<Party> findPageBy(Pageable page);

    void deleteByCreatedAtBefore(LocalDateTime createdAt);
}
