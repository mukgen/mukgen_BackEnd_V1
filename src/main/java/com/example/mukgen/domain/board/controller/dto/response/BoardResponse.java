package com.example.mukgen.domain.board.controller.dto.response;

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
}
