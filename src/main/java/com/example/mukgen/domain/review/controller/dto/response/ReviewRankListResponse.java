package com.example.mukgen.domain.review.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewRankListResponse {

    private List<ReviewRankResponse> reviewRankResponseList;
}
