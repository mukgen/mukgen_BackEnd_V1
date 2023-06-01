package com.example.mukgen.domain.review.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class ReviewWriterMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new ReviewWriterMismatchException();

    public ReviewWriterMismatchException() {
        super(ErrorCode.REVIEW_WRITER_MISMATCH);
    }
}
