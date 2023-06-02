package com.example.mukgen.domain.user.controller.response;

import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private String accountId;

    private String name;

    private String phoneNumber;

    private String profileUrl;

    public static UserInfoResponse of(User user){

        return UserInfoResponse.builder()
                .profileUrl(user.getProfileUrl())
                .accountId(user.getAccountId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
