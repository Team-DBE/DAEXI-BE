package com.example.daexi.domain.user.entity;

import com.example.spring_security.global.time.BaseTimeEntity;
import com.example.spring_security.domain.user.entity.type.Role;
import jakarta.persistence.*;
import lombok.*;



@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int user_number;

    @Column(nullable = false,length = 30)
    private String password;

    @Column(nullable = false)
    private String account_number;

    @Column(nullable = false)
    private String email;

    @Column(length = 20,nullable = false, unique = true)
    private String user_name;
}