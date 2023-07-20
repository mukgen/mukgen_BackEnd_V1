package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {

    private int riceId;

    private int count;

    private String review;

    private String userName;

    private RiceType riceType;

    private Long reviewId;

    public static ReviewResponse of(Review review){

        return ReviewResponse.builder()
                .reviewId(review.getId())
                .review(review.getReview())
                .riceType(review.getRice().getRiceType())
                .riceId(review.getRice().getId())
                .count(review.getCount())
                .userName(review.getUser().getName()).build();

    }
}
