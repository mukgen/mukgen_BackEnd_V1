package com.example.mukgen.domain.user.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class NoPermissionException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new NoPermissionException();

    public NoPermissionException() {
        super(ErrorCode.NO_PERMISSION);
    }
}
