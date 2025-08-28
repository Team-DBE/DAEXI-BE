package com.example.daexi.domain.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(nullable = false,length = 20)
    private String userName;

    @Column(nullable = false,length = 30)
    private String password;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String email;

    @Column(length = 1000)
    private String userDetail;

    @Column(nullable = false,unique = true)
    private String accountId;


}
