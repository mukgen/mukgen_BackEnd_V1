package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class ReviewNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new ReviewNotFoundException();

    public ReviewNotFoundException() {
        super(ErrorCode.REVIEW_NOT_FOUND);
    }
}
