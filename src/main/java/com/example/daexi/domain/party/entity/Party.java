package com.example.daexi.domain.party.entity;

import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(nullable = true , name = "party_password")
    private String partyPassword;

    @Column(nullable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "party_host")
    private String partyHost;

    @Column(nullable = false, name = "starting_latitude")
    private String startingLatitude; // 위도 - X

    @Column(nullable = false, name = "starting_longitude")
    private String startingLongitude; // 경도 - Y

    @Column(nullable = false, name = "ending_latitude")
    private String endingLatitude; // 위도 - X

    @Column(nullable = false, name = "ending_longitude")
    private String endingLongitude; // 경도 - Y

    @Column(nullable = false,unique = true)
    private String accountId;

    @OneToMany(mappedBy = "party")
    private List<Room> room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
}