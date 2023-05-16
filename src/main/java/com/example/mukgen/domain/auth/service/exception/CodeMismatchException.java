package com.example.mukgen.domain.auth.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class CodeMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new CodeMismatchException();

    public CodeMismatchException() {
        super(ErrorCode.CODE_MISMATCH);
    }
}
