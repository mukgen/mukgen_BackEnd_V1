package com.example.mukgen.domain.auth.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;

    private String refreshToken;
}
