package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.reviewcomment.controller.dto.response.ReviewCommentResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewMaximumResponse {

    private Integer count;

    private String userName;

    private String content;

    private List<ReviewCommentResponse> reviewCommentResponseList;

    public static ReviewMaximumResponse of(Review review){

        List<ReviewCommentResponse> reviewCommentResponseList =
                review.getReviewCommentList().stream()
                        .map(ReviewCommentResponse::of)
                        .toList();

        return ReviewMaximumResponse.builder()
                .content(review.getReview())
                .count(review.getCount())
                .userName(review.getReview())
                .reviewCommentResponseList(reviewCommentResponseList)
                .build();
    }
}