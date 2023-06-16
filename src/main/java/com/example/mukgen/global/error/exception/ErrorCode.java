package com.example.mukgen.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Bad Request
    PASSWORD_SAME(400,"비밀번호가 같습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),
    DELIVERY_PARTY_NOT_JOIN(400,"배달파티를 참여하지 않았습니다."),
    PASSWORD_CHECK_MISMATCH(400,"비밀번호가 같지 않습니다."),
    RICE_NOT_TODAY(400, "이 급식은 오늘 급식이 아닙니다."),
    DELIVERY_PARTY_FULL(400, "이 배달파티는 모집이 끝났습니다."),
    DELIVERY_PARTY_WRITER_CANT_LEAVE(400, "배달파티 작성자는 떠날 수 없습니다."),
    REVIEW_YET_TIME(400, "아직 작성할 수 없는 급식 입니다."),
    PASSWORD_MISMATCH(400, "비밀번호가 일치하지 않습니다."),

    // UnAuthorized
    EXPIRED_TOKEN(401 , "Expired token"),
    INVALID_TOKEN(401, "Invalid token"),

    // Forbidden
    BOARD_WRITER_MISMATCH(403, "게시글 작성자가 다릅니다."),
    REVIEW_WRITER_MISMATCH(403, "리뷰 작성자가 다릅니다."),
    COMMENT_WRITER_MISMATCH(403, "댓글 작성자가 다릅니다."),
    NO_PERMISSION(403, "권한이 없습니다."),
    MEAL_SUGGESTION_WRITER_MISMATCH(403, "급식건의 작성자가 다릅니다."),
    DELIVERY_PARTY_WRITER_MISMATCH(403, "배달파티 작성자가 다릅니다."),
    CODE_MISMATCH(403,"올바르지 않은 코드입니다."),

    // Not Found
    USER_NOT_FOUND(404,"찾을 수 없는 유저입니다."),
    BOARD_NOT_FOUND(404, "찾을 수 없는 게시글 입니다."),
    RICE_NOT_FOUND(404,"찾을 수 없는 급식입니다."),
    REVIEW_NOT_FOUND(404,"찾을 수 없는 리뷰입니다."),
    BOARD_COMMENT_NOT_FOUND(404,"찾을 수 없는 급식댓글 입니다."),
    DELIVERY_PARTY_NOT_FOUND(404,"찾을 수 없는 배달파티 입니다."),
    MEAL_SUGGESTION_NOT_FOUND(404, "찾을 수 없는 급식건의 입니다."),
    TOKEN_NOT_FOUND(404, "찾을 수 없는 토큰 입니다."),
    MUKGEN_PICK_NOT_FOUND(404, "찾을 수 없는 먹젠픽 입니다."),

    // Conflict
    USER_ALREADY_EXISTS(409, "이미 유저가 존재합니다."),
    REVIEW_ALREADY_EXISTS(409,"이미 리뷰가 존재합니다."),
    DELIVERY_PARTY_ALREADY_EXISTS(409,"이미 배달파티가 존재합ㄴ디ㅏ."),
    DELIVERY_PARTY_IN_PROGRESS(409, "이미 모집중인 배달파티가 존재합니다."),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int statusCode;

    private final String message;

}
