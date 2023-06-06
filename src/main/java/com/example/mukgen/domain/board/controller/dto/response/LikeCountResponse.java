package com.example.mukgen.domain.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeCountResponse {

    private Integer boardLike;
}
