package com.example.mukgen.domain.user.controller.response;

import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserProfileResponse {

    private String name;

    private String userId;

    public static UserProfileResponse of(User user){

        return UserProfileResponse.builder()
                .name(user.getName())
                .userId(user.getAccountId()).build();
    }

}
