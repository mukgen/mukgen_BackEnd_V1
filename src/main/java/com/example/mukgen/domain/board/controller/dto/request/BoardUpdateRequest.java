package com.example.mukgen.domain.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateRequest {

    @Size(min = 1, max = 30, message = "글 제목은 최소 1자, 최대 30자 입니다")
    private String title;

    @Size(min = 1, max = 300, message = "글 제목은 최소 1자, 최대 300자 입니다")
    private String content;

}
