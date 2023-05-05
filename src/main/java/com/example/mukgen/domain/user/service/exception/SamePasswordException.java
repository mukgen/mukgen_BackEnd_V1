package com.example.mukgen.domain.user.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class SamePasswordException extends BusinessException {

    public static final BusinessException EXCEPTION = new SamePasswordException();

    public SamePasswordException() {
        super(ErrorCode.PASSWORD_SAME);
    }
}
