package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewMaximumResponse {

    private Integer count;

    private String userName;

    private String content;

    private LocalDateTime createdAt;

    private List<ReviewCommentResponse> reviewCommentResponseList;

    public static ReviewMaximumResponse of(Review review){

        List<ReviewCommentResponse> reviewCommentResponseList =
                review.getReviewCommentList().stream()
                        .map(ReviewCommentResponse::of)
                        .toList();

        return ReviewMaximumResponse.builder()
                .createdAt(review.getCreatedAt())
                .content(review.getReview())
                .count(review.getCount())
                .userName(review.getUser().getNickname())
                .reviewCommentResponseList(reviewCommentResponseList)
                .build();
    }
}
