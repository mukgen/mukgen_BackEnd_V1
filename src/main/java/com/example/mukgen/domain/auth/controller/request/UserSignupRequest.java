package com.example.mukgen.domain.auth.controller.request;

import lombok.Getter;

@Getter
public class UserSignupRequest {

    private String name;

    private String userId;

    private String password;

    private String phoneNumber;
}
