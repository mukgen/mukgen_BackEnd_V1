package com.example.mukgen.domain.auth.controller.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class TokenResponse {

    private String accessToken;
}
