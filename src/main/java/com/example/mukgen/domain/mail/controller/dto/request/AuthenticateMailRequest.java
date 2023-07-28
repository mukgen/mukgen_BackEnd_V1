package com.example.mukgen.domain.mail.controller.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class AuthenticateMailRequest {

    @Pattern(regexp = "^\\w+(@dsm+\\.hs+\\.kr)$",
            message = "메일 주소는 dsm.hs.kr로 끝나는 메일 주소여야 합니다.")
    private String mail;

    @NotNull(message = "인증 번호를 입력해주세요.")
    private String code;
}
