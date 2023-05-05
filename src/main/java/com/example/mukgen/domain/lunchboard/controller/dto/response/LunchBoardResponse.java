package com.example.mukgen.domain.lunchboard.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LunchBoardResponse {

    private String title;

    private String content;

    private String username;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean deleted;
}
