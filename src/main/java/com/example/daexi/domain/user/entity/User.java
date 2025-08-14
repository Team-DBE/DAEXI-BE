package com.example.daexi.domain.user.entity;

import com.example.daexi.domain.daexiParty.entity.Party;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "user_number")
    private int userNumber;

    @Column(nullable = false,length = 30)
    private String password;

    @Column(nullable = false, name = "acccount_number")
    private String accountNumber;

    @Column(nullable = false)
    private String email;

    @Column(length = 20, nullable = false, unique = true, name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;
}