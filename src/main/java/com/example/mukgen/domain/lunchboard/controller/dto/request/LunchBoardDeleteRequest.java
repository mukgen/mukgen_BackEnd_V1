package com.example.mukgen.domain.lunchboard.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LunchBoardDeleteRequest {

    public Long boardId;
}