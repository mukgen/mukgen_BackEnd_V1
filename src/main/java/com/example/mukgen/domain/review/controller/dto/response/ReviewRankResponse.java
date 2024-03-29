package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRankResponse {

    private String profileUrl;

    private String userName;

    private Integer reviewCount;

    private String averageReview;

    public static ReviewRankResponse of(User user){

        float aveCount = (user.getReviewList()
                            .stream()
                            .mapToInt(Review::getCount)
                            .sum())/(float)user.getReviewList().size();

        return ReviewRankResponse.builder()
                .profileUrl(user.getProfileUrl())
                .userName(user.getNickname())
                .reviewCount(user.getReviewList().size())
                .averageReview(String.format("%.2f", aveCount))
                .build();
    }
}
