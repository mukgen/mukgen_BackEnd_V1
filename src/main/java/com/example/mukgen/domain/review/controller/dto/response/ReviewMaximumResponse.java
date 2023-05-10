package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewMaximumResponse {

    private Integer count;

    private String userName;

    private String content;

    public static ReviewMaximumResponse of(Review review){

        return ReviewMaximumResponse.builder()
                .content(review.getReview())
                .count(review.getCount())
                .userName(review.getReview())
                .build();
    }
}
