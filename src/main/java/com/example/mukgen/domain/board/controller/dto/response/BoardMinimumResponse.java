package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardMinimumResponse {

    private String title;

    private String content;

    private String userName;

    private int likeCount;

    private int commentCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean isUpdated;

    public static BoardMinimumResponse of(Board board){

        return BoardMinimumResponse.builder()
                .createAt(board.getCreateAt())
                .commentCount(board.getCommentCount())
                .title(board.getTitle())
                .content(board.getContent())
                .userName(board.getUser().getName())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .isUpdated(board.getIsUpdated())
                .updateAt(board.getUpdateAt())
                .build();
    }
}
