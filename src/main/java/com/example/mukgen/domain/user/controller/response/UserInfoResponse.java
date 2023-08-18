package com.example.mukgen.domain.user.controller.response;

import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {

    private String accountId;

    private UserRole userRole;

    private String name;

    private String profileUrl;

    private String mail;

    public static UserInfoResponse of(User user){

        return UserInfoResponse.builder()
                .accountId(user.getAccountId())
                .userRole(user.getRole())
                .name(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .mail(user.getMail())
                .build();
    }
}
