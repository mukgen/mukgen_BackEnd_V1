package com.example.mukgen.domain.rice.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class RiceNotTodayException extends BusinessException {

    public static final BusinessException EXCEPTION = new RiceNotTodayException();

    public RiceNotTodayException() {
        super(ErrorCode.RICE_NOT_TODAY);
    }
}
