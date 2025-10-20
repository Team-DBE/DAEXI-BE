package com.example.daexi.domain.room.entity.repository;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByUserAndParty(User user, Party party);

    List<Room> findByParty(Party party);
}
