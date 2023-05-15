package com.example.mukgen.domain.boardcomment.controller.dto.response;

import com.example.mukgen.domain.boardcomment.entity.BoardComment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardCommentResponse {

    private String writer;

    private String content;

    private LocalDateTime createAt;

    private Long boardCommentId;

    public static BoardCommentResponse of(BoardComment comment){
        return BoardCommentResponse.builder()
                .createAt(comment.getCreatedAt())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .boardCommentId(comment.getId())
                .build();
    }
}
