package com.example.daexi.domain.party.entity.repository;

import com.example.daexi.domain.party.entity.Party;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findAllBy(Pageable page);

    void deleteByArriveAtBefore(LocalDateTime arriveAtBefore);
}
