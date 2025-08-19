package com.example.daexi.domain.user.entity;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.room.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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

    @Column(length = 20, nullable = false, unique = true, name = "user_name")
    private String userName;

    @OneToMany(mappedBy ="user")
    private List<Room> rooms;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<User> users;
}