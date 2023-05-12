package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardPopularResponse {

    private String title;

    private Integer commentCount;

    private Integer viewCount;

    public static BoardPopularResponse of(Board board){

        return BoardPopularResponse.builder()
                .title(board.getTitle())
                .viewCount(board.getViewCount())
                .commentCount(board.getCommentCount())
                .build();
    }

}
