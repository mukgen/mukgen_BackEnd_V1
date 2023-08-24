package com.example.mukgen.domain.auth.controller.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DuplicateUserResponse {

    private boolean isDuplicate;

    public DuplicateUserResponse(boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
    }
}
