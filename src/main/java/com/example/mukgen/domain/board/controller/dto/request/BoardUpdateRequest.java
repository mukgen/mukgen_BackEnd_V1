package com.example.mukgen.domain.board.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardUpdateRequest {

    private String title;

    private String content;

}
