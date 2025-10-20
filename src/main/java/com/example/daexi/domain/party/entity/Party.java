package com.example.daexi.domain.party.entity;

import com.example.daexi.domain.room.entity.Room;
import com.example.daexi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(unique = true, nullable = false)
    private Long partyId;

    @Column(nullable = false)
    private String partyName;

    @Column(nullable = true)
    private String partyPassword;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime arriveAt;

    @Column(nullable = false)
    private String startingLatitude; // 위도 - X

    @Column(nullable = false)
    private String startingLongitude; // 경도 - Y

    @Column(nullable = false)
    private String endingLatitude; // 위도 - X

    @Column(nullable = false)
    private String endingLongitude; // 경도 - Y

    @OneToMany(mappedBy = "party")
    private List<Room> room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User partyhost;

    public Party updateParty(String partyName,
                             String partyPassword,
                             LocalDateTime arriveAt,
                             String startingLatitude,
                             String startingLongitude,
                             String endingLatitude,
                             String endingLongitude) {
        this.partyName = partyName;
        this.partyPassword = partyPassword;
        this.arriveAt = arriveAt;
        this.startingLatitude = startingLatitude;
        this.startingLongitude = startingLongitude;
        this.endingLatitude = endingLatitude;
        this.endingLongitude = endingLongitude;
        return this;
    }
}