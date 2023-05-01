package com.example.mukgen.domain.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardCreateRequest {

    private String title;

    private String content;

    public BoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
