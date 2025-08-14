package com.example.daexi.domain.daexiParty.entity;

import com.example.daexi.domain.user.entity.User;
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

    @Column(nullable = true , name = "party_password")
    private String partyPassword;

    @Column(nullable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "party_host")
    private String partyHost;

    @Column(nullable = false, name = "starting_point")
    private String startingPoint;

    @Column(nullable = false, name = "ending_point")
    private String endingPoint;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
