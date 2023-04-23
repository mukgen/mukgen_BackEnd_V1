package com.example.mukgen.domain.lunchboard.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LunchBoardCreateRequest {

    private String title;

    private String content;

    public LunchBoardCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
