package com.example.mukgen.domain.user.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
