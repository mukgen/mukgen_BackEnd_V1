package com.example.mukgen.domain.mail.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SendMailRequest {

    @Pattern(regexp = "^\\w+(@dsm+\\.hs+\\.kr)$",
            message = "메일 주소는 dsm.hs.kr로 끝나는 메일 주소여야 합니다.")
    private String mail;
}
