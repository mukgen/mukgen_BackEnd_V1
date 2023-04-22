package com.example.mukgen.domain.board.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardNotFoundException();

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}
