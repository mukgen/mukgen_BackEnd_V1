package com.example.mukgen.domain.review.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewRankListResponse {

    private List<ReviewRankResponse> reviewRankResponseList;
}
