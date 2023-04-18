package com.example.mukgen.domain.auth.controller.reponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {
    private String accessToken;
    //test
}
