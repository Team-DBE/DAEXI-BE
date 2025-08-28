package com.example.daexi.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

}
