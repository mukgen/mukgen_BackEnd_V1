package com.example.mukgen.domain.auth.controller.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private TokenResponse tokenResponse;

    private String message;
}
