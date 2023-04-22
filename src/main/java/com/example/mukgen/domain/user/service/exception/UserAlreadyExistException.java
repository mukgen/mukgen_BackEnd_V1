package com.example.mukgen.domain.user.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();

    public UserAlreadyExistException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
