package com.example.mukgen.domain.reviewcomment.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewCommentListResponse {

    private List<ReviewCommentResponse> reviewCommentResponseList;
}
