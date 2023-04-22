package com.example.mukgen.domain.user.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
