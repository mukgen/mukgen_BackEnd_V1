package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class ReviewAlreadyExistsException extends BusinessException {

    public static final BusinessException EXCEPTION = new ReviewAlreadyExistsException();

    public ReviewAlreadyExistsException() {
        super(ErrorCode.REVIEW_ALREADY_EXISTS);
    }
}
