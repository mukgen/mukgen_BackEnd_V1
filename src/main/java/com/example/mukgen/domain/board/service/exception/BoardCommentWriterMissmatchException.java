package com.example.mukgen.domain.board.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardCommentWriterMissmatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardCommentWriterMissmatchException();

    public BoardCommentWriterMissmatchException() {
        super(ErrorCode.BOARD_WRITER_MISMATCH);
    }
}
