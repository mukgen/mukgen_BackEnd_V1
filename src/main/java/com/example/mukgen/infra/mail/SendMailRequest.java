package com.example.mukgen.infra.mail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SendMailRequest {

    @Pattern(regexp = "^[a-zA-Z0-9.]@dsm.hs.kr$")
    private String mail;
}
