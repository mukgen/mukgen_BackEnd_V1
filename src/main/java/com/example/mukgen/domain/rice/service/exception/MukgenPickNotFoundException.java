package com.example.mukgen.domain.rice.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MukgenPickNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new MukgenPickNotFoundException();

    public MukgenPickNotFoundException() {
        super(ErrorCode.MUKGEN_PICK_NOT_FOUND);
    }
}
