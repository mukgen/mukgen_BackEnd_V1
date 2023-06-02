package com.example.mukgen.domain.auth.controller.reponse;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private TokenResponse tokenResponse;

    private String message;
}
