package com.example.mukgen.domain.user.controller.response;

import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {

    private String accountId;

    private String nickname;

    private String profileUrl;

    private String phoneNumber;

    private String mail;

    public static UserInfoResponse of(User user){

        return UserInfoResponse.builder()
                .accountId(user.getAccountId())
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .phoneNumber(user.getPhoneNumber())
                .mail(user.getMail())
                .build();
    }
}
