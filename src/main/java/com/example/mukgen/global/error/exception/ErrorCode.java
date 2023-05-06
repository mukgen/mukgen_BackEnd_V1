package com.example.mukgen.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Bad Request
    PASSWORD_SAME(400,"Same Password"),
    BAD_REQUEST(400, "Bad request"),

    // Un Authorized
    EXPIRED_TOKEN(401 , "Expired token"),
    INVALID_TOKEN(401, "Invalid token"),

    // Forbidden
    BOARD_WRITER_MISMATCH(403, "Board writer mismatch"),

    // Not Found
    USER_NOT_FOUND(404,"User not found"),
    PASSWORD_MISMATCH(404, "Password mismatch"),
    BOARD_NOT_FOUND(404, "Board not found"),
    MEAL_NOT_FOUND(404,"Meal not found"),

    // Conflict
    USER_ALREADY_EXISTS(409, "User already exists"),
    REVIEW_ALREADY_EXISTS(409,"Review already exists"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int statusCode;

    private final String message;

}
