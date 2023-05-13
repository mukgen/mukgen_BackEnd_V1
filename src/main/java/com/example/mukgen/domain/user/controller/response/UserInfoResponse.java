package com.example.mukgen.domain.user.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {

    private String accountId;

    private String name;

    private String phoneNumber;

}
