package com.example.mukgen.global.error;

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
}
