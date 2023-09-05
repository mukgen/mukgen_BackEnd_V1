package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewStatisticsResponse {

    private int score5;

    private int score4;

    private int score3;

    private int score2;

    private int score1;

    private String average;

    public static ReviewStatisticsResponse fromReviewList(List<Review> reviews) {
        int score5 = 0;
        int score4 = 0;
        int score3 = 0;
        int score2 = 0;
        int score1 = 0;
        float total = 0;

        for (Review review : reviews) {
            int count = review.getCount();
            total += count;
            if (count >= 5) {
                score5++;
            } else if (count >= 4) {
                score4++;
            } else if (count >= 3) {
                score3++;
            } else if (count >= 2) {
                score2++;
            } else if (count >= 1) {
                score1++;
            }
        }

        return ReviewStatisticsResponse.builder()
                .score5(score5)
                .score4(score4)
                .score3(score3)
                .score2(score2)
                .score1(score1)
                .average(String.format("%.2f", (total / reviews.size())))
                .build();
    }

}
