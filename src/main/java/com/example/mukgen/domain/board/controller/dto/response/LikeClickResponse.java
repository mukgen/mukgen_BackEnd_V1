package com.example.mukgen.domain.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeClickResponse {

    private Integer boardLike;

    private boolean isLiked;
}
