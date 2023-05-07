package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class ReviewWriterMissMatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new ReviewNotFoundException();

    public ReviewWriterMissMatchException() {
        super(ErrorCode.REVIEW_WRITER_MISMATCH);
    }
}
