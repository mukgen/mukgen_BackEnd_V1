package com.example.mukgen.domain.boardcomment.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardCommentWriterMissMatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardCommentWriterMissMatchException();

    public BoardCommentWriterMissMatchException() {
        super(ErrorCode.BOARD_WRITER_MISMATCH);
    }
}
