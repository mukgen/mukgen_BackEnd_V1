package com.example.mukgen.domain.user.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserProfileResponse {

    private String name;

    private String userId;

}
