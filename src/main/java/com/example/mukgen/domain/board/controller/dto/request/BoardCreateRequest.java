package com.example.mukgen.domain.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequest {

    @Size(min = 1, max = 30, message = "글 제목은 최소 1자, 최대 30자 입니다")
    private String title;

    @Size(min = 1, max = 300, message = "글 내용은 최소 1자, 최대 300자 입니다")
    private String content;

    public BoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
