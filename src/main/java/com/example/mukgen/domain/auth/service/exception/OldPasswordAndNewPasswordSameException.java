package com.example.mukgen.domain.auth.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class OldPasswordAndNewPasswordSameException extends BusinessException {

    public static final BusinessException EXCEPTION = new OldPasswordAndNewPasswordSameException();

    public OldPasswordAndNewPasswordSameException() {
        super(ErrorCode.OLD_PASSWORD_AND_NEW_PASSWORD_SAME);
    }
}
