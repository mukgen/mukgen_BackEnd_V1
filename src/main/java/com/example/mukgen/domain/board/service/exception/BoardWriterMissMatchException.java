package com.example.mukgen.domain.board.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardWriterMissMatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardWriterMissMatchException();

    public BoardWriterMissMatchException() {
        super(ErrorCode.BOARD_WRITER_MISMATCH);
    }
}
