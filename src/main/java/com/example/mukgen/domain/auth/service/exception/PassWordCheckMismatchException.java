package com.example.mukgen.domain.auth.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class PassWordCheckMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new PassWordCheckMismatchException();

    public PassWordCheckMismatchException() {
        super(ErrorCode.PASSWORD_CHECK_MISMATCH);
    }
}
