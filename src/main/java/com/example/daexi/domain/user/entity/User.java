package com.example.daexi.domain.user.entity;

import com.example.daexi.domain.room.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,length = 20)
    private String userName;

    @Column(nullable = false,length = 30)
    private String password;
  
    @Column(nullable = false)
    private String accountNumber;

    @Column(length = 1000)
    private String userDetail;

    @Column(nullable = false,unique = true)
    private String accountId;

    @Column(nullable = false)
    private int userNumber;
  
    @OneToMany(mappedBy ="user")
    private List<Room> rooms;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<User> users;

}
