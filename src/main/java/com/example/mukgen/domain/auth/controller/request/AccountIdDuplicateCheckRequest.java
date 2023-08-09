package com.example.mukgen.domain.auth.controller.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountIdDuplicateCheckRequest {

    @Size(min = 5, max = 15, message = "아이디는 최소 5자, 최대 15자 입니다.")
    private String accountId;
}
