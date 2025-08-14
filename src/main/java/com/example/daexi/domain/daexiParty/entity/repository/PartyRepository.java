package com.example.daexi.domain.daexiParty.entity.repository;

import com.example.daexi.domain.daexiParty.entity.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Page<Party> findPageBy(Pageable page);

    @Query(value = "select * from party where match(Starting_point) against(?1)", nativeQuery = true)
    List<Party> getPartyById(String text);

    void deleteByCreatedAtBefore(LocalDateTime createdAt);
}
