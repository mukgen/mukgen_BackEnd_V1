package com.example.mukgen.domain.auth.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;

    private Date accessTokenExp;

    private String refreshToken;

    private Date refreshTokenExp;
}
