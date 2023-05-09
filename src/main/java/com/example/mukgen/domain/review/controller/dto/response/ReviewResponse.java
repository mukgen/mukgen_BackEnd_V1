package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private int riceId;

    private int count;

    private String content;

    private String userName;

    public static ReviewResponse of(Review review){

        return ReviewResponse.builder()
                .riceId(review.getRice().getId())
                .content(review.getReview())
                .count(review.getCount())
                .userName(review.getUser().getName()).build();

    }
}
