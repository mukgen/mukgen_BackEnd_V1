package com.example.mukgen.domain.reviewcomment.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class ReviewCommentListResponse {

    private List<ReviewCommentResponse> reviewCommentResponseList;
}
