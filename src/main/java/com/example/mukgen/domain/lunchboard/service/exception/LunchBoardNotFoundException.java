package com.example.mukgen.domain.lunchboard.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class LunchBoardNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new LunchBoardNotFoundException();

    public LunchBoardNotFoundException() {
        super(ErrorCode.LUNCH_BOARD_NOT_FOUND);
    }
}
