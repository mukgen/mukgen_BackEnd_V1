package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class RiceInaccessibleException extends BusinessException {

    public static final BusinessException EXCEPTION = new RiceInaccessibleException();

    public RiceInaccessibleException() {
        super(ErrorCode.RICE_INACCESSIBLE);
    }
}
