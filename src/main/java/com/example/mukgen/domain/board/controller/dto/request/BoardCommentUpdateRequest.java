package com.example.mukgen.domain.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCommentUpdateRequest {

    @Size(min = 1, max = 100, message = "댓글은 최소 1자, 최대 100자 입니다")
    private String content;
}
