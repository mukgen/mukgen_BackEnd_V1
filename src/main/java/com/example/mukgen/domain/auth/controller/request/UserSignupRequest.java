package com.example.mukgen.domain.auth.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignupRequest {


    @Size(max = 4, message = "이름은 최대 4자 입니다.")
    private String name;

    @Size(max = 15, message = "아이디는 최대 15자 입니다.")
    private String userId;

    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*",
            message = "비밀번호는 최대 20글자이고, 특수문자 한개가 포함되어야 합니다.")
    private String password;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$")
    private String phoneNumber;
}
