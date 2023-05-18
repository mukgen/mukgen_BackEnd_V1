package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.BoardComment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardCommentResponse {

    private String writer;

    private String content;

    private LocalDateTime createdAt;

    private Long boardCommentId;

    public static BoardCommentResponse of(BoardComment comment){
        return BoardCommentResponse.builder()
                .createdAt(comment.getCreatedAt())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .boardCommentId(comment.getId())
                .build();
    }
}
