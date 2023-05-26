package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class ReviewYetTimeException extends BusinessException {

    public static final BusinessException EXCEPTION = new ReviewYetTimeException();
    public ReviewYetTimeException() {
        super(ErrorCode.REVIEW_YET_TIME);
    }
}
