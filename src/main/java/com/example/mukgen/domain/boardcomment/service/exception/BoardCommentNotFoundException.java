package com.example.mukgen.domain.boardcomment.service.exception;

import com.example.mukgen.domain.review.service.exception.ReviewAlreadyExistsException;
import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class BoardCommentNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardCommentNotFoundException();

    public BoardCommentNotFoundException() {
        super(ErrorCode.BOARD_COMMENT_NOT_FOUND);
    }
}
