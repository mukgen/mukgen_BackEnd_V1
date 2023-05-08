package com.example.mukgen.domain.boardcomment.controller.dto.request;

import lombok.Data;

@Data
public class BoardCommentCreateRequest {

    private Long boardId;

    private String content;

}
