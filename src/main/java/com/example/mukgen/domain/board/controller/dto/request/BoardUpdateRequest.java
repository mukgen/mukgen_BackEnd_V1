package com.example.mukgen.domain.board.controller.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardUpdateRequest {

    private String title;

    private String content;

}
