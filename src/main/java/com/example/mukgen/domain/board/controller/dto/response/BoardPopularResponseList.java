package com.example.mukgen.domain.board.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoardPopularResponseList {

    private List<BoardPopularResponse> boardPopularResponseList;

}
