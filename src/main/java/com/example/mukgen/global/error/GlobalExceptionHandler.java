package com.example.mukgen.global.error;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidateException(MethodArgumentNotValidException e){

        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        ErrorResponse response = ErrorResponse.of(errorCode, e.getFieldError().getDefaultMessage());

       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode,errorCode.getMessage());

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e){
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        ErrorResponse response = ErrorResponse.of(errorCode,errorCode.getMessage());

        return new ResponseEntity<>(response,HttpStatus.valueOf(errorCode.getStatusCode()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
