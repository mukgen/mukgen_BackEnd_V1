package com.example.mukgen.domain.lunchboard.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LunchBoardUpdateRequest {

    private String title;

    private String content;
}