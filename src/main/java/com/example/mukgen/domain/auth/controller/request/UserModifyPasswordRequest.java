package com.example.mukgen.domain.auth.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserModifyPasswordRequest {

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String oldPassword;

    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*",
            message = "비밀번호는 최대 20글자이고, 특수문자 한개가 포함되어야 합니다.")
    private String newPassword;
}
