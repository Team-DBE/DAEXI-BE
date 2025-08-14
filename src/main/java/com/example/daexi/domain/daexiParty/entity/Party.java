package com.example.daexi.domain.daexiParty.entity;


import com.example.daexi.domain.room.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "party")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Party {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "party_id")
    private Long partyId;

    @Column(nullable = false, name = "party_name")
    private String partyName;

    @Column(nullable = false, name = "party_password")
    private String partyPassword;

    @Column(nullable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "partyhost")
    private String partyHost;

    @Column(nullable = false, name = "starting_point")
    private String startingPoint;

    @Column(nullable = false, name = "ending_point")
    private String endingPoint;

    @OneToMany(mappedBy = "party")
    private List<Room> rooms = new ArrayList<>();

}
