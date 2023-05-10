package com.example.mukgen.domain.rice.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class RiceNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new RiceNotFoundException();

    public RiceNotFoundException() {
        super(ErrorCode.RICE_NOT_FOUND);
    }
}
