package com.example.mukgen.domain.mail.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class NeverValidMailException extends BusinessException {

    public static final BusinessException EXCEPTION = new NeverValidMailException();

    public NeverValidMailException() {
        super(ErrorCode.NEVER_VALID_MAIL);
    }
}
