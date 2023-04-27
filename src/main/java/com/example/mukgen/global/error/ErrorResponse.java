package com.example.mukgen.global.error;

import com.example.mukgen.global.error.exception.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String message;

    private Integer status;

    private LocalDateTime timestamp;

    private String description;

    public static ErrorResponse of(ErrorCode errorCode, String description) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatusCode())
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }

    public static ErrorResponse of(int errorCode, String description) {
        return ErrorResponse.builder()
                .message(description)
                .status(errorCode)
                .timestamp(LocalDateTime.now())
                .description(description)
                .build();
    }
}
