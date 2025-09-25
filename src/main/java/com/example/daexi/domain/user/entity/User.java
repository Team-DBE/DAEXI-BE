package com.example.daexi.domain.user.entity;

import com.example.daexi.domain.room.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,length = 20)
    private String userName;

    @Column(nullable = false,length = 60)
    private String password;

    @Column(length = 1000)
    private String userDetail;

    @OneToMany(mappedBy ="user")
    private List<Room> rooms;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<User> users;

}