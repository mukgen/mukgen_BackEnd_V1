package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.boardcomment.controller.dto.response.BoardCommentResponse;
import com.example.mukgen.domain.like.controller.dto.response.LikeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
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

    public static BoardMaximumResponse of(Board board){

        List<LikeResponse> likeResponses = board.getLikesList().stream()
                .map(it -> LikeResponse.builder()
                        .userName(it.getUserName()).build()).toList();

        List<BoardCommentResponse> boardCommentResponseList = board.getBoardCommentList().stream()
                .map(BoardCommentResponse::of).toList();

        return  BoardMaximumResponse.builder()
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