package com.example.mukgen.domain.board.controller.dto.response;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.like.controller.dto.response.LikeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BoardResponse {

    private String title;

    private String content;

    private String userName;

    private int likeCount;

    private List<LikeResponse> likeResponseList;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public static BoardResponse of(Board board){
        List<LikeResponse> likeResponses = board.getLikesList().stream()
                .map(it -> LikeResponse.builder()
                        .boardId(it.getBoard().getId())
                        .userName(it.getUserName()).build()).toList();

        return  BoardResponse.builder()
                .title(board.getTitle())
                .likeResponseList(likeResponses)
                .content(board.getContent())
                .userName(board.getUser().getName())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .createAt(board.getCreateAt())
                .updateAt(board.getUpdateAt()).build();
    }
}
