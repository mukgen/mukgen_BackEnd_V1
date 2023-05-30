package com.example.mukgen.global.error.exception;

public class InvalidTokenException extends BusinessException{

    public static final BusinessException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
