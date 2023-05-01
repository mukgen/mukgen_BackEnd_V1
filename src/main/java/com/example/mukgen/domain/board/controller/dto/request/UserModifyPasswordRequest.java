package com.example.mukgen.domain.board.controller.dto.request;

import lombok.Data;

@Data
public class UserModifyPasswordRequest {

    private String oldPassword;

    private String newPassword;
}