package com.example.mukgen.domain.board.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardCommentNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardCommentNotFoundException();

    public BoardCommentNotFoundException() {
        super(ErrorCode.BOARD_COMMENT_NOT_FOUND);
    }
}
