package com.example.mukgen.domain.auth.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class PasswordSameException extends BusinessException {

    public static final BusinessException EXCEPTION = new PasswordSameException();

    public PasswordSameException() {
        super(ErrorCode.PASSWORD_SAME);
    }
}
