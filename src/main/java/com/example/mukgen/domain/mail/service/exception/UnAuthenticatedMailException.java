package com.example.mukgen.domain.mail.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class UnAuthenticatedMailException extends BusinessException {

    public static final BusinessException EXCEPTION = new UnAuthenticatedMailException();

    public UnAuthenticatedMailException() {
        super(ErrorCode.UNAUTHENTICATED_MAIL);
    }
}
