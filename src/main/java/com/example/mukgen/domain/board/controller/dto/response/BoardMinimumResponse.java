package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardMinimumResponse {

    private Long boardId;

    private String title;

    private String content;

    private String userName;

    private int likeCount;

    private int commentCount;

    private int viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isUpdated;

    public static BoardMinimumResponse of(Board board){

        return BoardMinimumResponse.builder()
                .boardId(board.getId())
                .createdAt(board.getCreatedAt())
                .commentCount(board.getCommentCount())
                .title(board.getTitle())
                .content(board.getContent())
                .userName(board.getUser().getNickname())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .isUpdated(board.getIsUpdated())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
