package com.example.mukgen.domain.board.controller.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardPopularListResponse {

    private List<BoardPopularResponse> boardPopularResponseList;

}
