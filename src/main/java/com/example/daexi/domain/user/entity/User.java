package com.example.daexi.domain.user.entity;

import com.example.daexi.domain.party.entity.Party;
import com.example.daexi.domain.room.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,length = 20)
    private String userName;

    @JsonIgnore
    @Column(nullable = false,length = 60)
    private String password;

    @Column(nullable = false)
    private String accountNumber;

    @Column(length = 1000)
    private String userDetail;

    @Column(nullable = false,unique = true)
    private String accountId;

    @Column(nullable = false)
    private int userNumber;

    public void updateDetail(String userName, String accountNumber, String userDetail, String accountId, String password){
        this.userName = userName;
        this.accountId = accountId;
        this.userDetail = userDetail;
        this.accountNumber = accountNumber;
        this.password = password;
    }
}