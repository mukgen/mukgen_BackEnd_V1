package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRankResponse {

    private String userName;

    private Integer reviewCount;

    private Float averageReview;

    public static ReviewRankResponse of(User user){

        float aveCount = (user.getReviewList()
                            .stream()
                            .mapToInt(Review::getCount)
                            .sum())/(float)user.getReviewList().size();

        return ReviewRankResponse.builder()
                .userName(user.getName())
                .reviewCount(user.getReviewList().size())
                .averageReview(aveCount)
                .build();
    }
}