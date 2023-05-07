package com.example.mukgen.domain.review.entity.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private int count;

    private String content;

    private String userName;

    public static ReviewResponse of(Review review){

        return ReviewResponse.builder()
                .content(review.getReviewContent())
                .count(review.getCount())
                .userName(review.getReviewContent()).build();

    }
}
