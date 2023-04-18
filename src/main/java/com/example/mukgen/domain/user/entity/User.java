package com.example.mukgen.domain.user.entity;

import com.example.mukgen.domain.user.entity.type.UserRole;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "user_id")
    private String userId;

    private String name;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;


}
