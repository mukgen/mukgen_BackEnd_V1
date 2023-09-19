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

    private String userNickname;

    private String content;

    private LocalDateTime createdAt;

    private String imageUrl;

    private List<ReviewCommentResponse> reviewCommentResponseList;

    public static ReviewMaximumResponse of(Review review){

        List<ReviewCommentResponse> reviewCommentResponseList =
                review.getReviewCommentList().stream()
                        .map(ReviewCommentResponse::of)
                        .toList();

        return ReviewMaximumResponse.builder()
                .imageUrl(review.getImageUrl())
                .createdAt(review.getCreatedAt())
                .content(review.getReview())
                .count(review.getCount())
                .userNickname(review.getUser().getNickname())
                .reviewCommentResponseList(reviewCommentResponseList)
                .build();
    }
}
