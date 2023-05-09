package com.example.mukgen.domain.auth.controller.request;

import lombok.Data;

@Data
public class UserModifyPasswordRequest {

    private String oldPassword;

    private String newPassword;
}
