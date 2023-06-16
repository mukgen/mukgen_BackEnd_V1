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

    private Integer grade;

    private Integer classNum;

    private Integer studentNum;

    private String profileUrl;


    public static UserInfoResponse of(User user){

        return UserInfoResponse.builder()
                .userRole(user.getRole())
                .studentNum(user.getStudentNum())
                .classNum(user.getClassNum())
                .grade(user.getGrade())
                .profileUrl(user.getProfileUrl())
                .accountId(user.getAccountId())
                .name(user.getName())
                .build();
    }

}
