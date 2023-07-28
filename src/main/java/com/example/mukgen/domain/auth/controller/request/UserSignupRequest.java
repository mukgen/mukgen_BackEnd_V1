package com.example.mukgen.domain.auth.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignupRequest {


    @Size(min = 1, max = 4, message = "이름은 최소 1자, 최대 4자 입니다.")
    private String name;

    @Size(min = 5, max = 15, message = "아이디는 최소 5자, 최대 15자 입니다.")
    private String accountId;

    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*",
            message = "비밀번호는 최대 20글자이고, 특수문자 한개가 포함되어야 합니다.")
    private String password;

    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*",
            message = "비밀번호는 최대 20글자이고, 특수문자 한개가 포함되어야 합니다.")
    private String passwordCheck;

    @Pattern(regexp = "^010\\d{4}\\d{4}$",message = "(-) 없이 입력해 주세요. ")
    private String phoneNumber;

    @Pattern(regexp = "^\\w+(@dsm+\\.hs+\\.kr)$",
            message = "메일 주소는 dsm.hs.kr로 끝나는 메일 주소여야 합니다.")
    private String mail;
}
