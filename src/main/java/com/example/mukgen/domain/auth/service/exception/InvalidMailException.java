package com.example.mukgen.domain.auth.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class InvalidMailException extends BusinessException {

    public static final BusinessException EXCEPTION = new InvalidMailException();

    public InvalidMailException() {
        super(ErrorCode.INVALID_MAIL);
    }
}

