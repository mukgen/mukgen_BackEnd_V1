package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class BoardPopularResponse {

    private Long boardId;

    private String title;

    private Integer commentCount;

    private Integer viewCount;

    public static BoardPopularResponse of(Board board){

        return BoardPopularResponse.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .viewCount(board.getViewCount())
                .commentCount(board.getCommentCount())
                .build();
    }

}
