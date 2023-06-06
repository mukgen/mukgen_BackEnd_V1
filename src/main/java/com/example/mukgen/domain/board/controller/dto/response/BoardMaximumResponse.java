package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BoardMaximumResponse {

    private String title;

    private String content;

    private String userName;

    private int likeCount;

    private int commentCount;

    private List<LikeResponse> likeResponseList;

    private List<BoardCommentResponse> boardCommentList;

    private int viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isLiked;

    public static BoardMaximumResponse of(Board board, boolean isLiked){

        List<LikeResponse> likeResponses = board.getLikesList().stream()
                .map(it -> LikeResponse.builder()
                        .userName(it.getUserName()).build()).toList();

        List<BoardCommentResponse> boardCommentResponseList = board.getBoardCommentList().stream()
                .map(BoardCommentResponse::of).toList();

        return  BoardMaximumResponse.builder()
                .isLiked(isLiked)
                .commentCount(boardCommentResponseList.size())
                .boardCommentList(boardCommentResponseList)
                .title(board.getTitle())
                .likeResponseList(likeResponses)
                .content(board.getContent())
                .userName(board.getUser().getName())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt()).build();
    }
}