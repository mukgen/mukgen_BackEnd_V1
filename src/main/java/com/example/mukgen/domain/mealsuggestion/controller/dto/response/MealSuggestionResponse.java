package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MealSuggestionResponse {

    private Long id;

    private String content;

    private int likeCount;

    private int dislikeCount;

    private boolean isChecked;

    private int month;

    private int day;

    private int hour;

    private int minute;

    public static MealSuggestionResponse of(MealSuggestion mealSuggestion) {

        return MealSuggestionResponse.builder()
                .id(mealSuggestion.getId())
                .content(mealSuggestion.getContent())
                .likeCount(mealSuggestion.getLikeCount())
                .dislikeCount(mealSuggestion.getDislikeCount())
                .isChecked(mealSuggestion.isChecked())
                .month(mealSuggestion.getMonth())
                .day(mealSuggestion.getDay())
                .hour(mealSuggestion.getHour())
                .minute(mealSuggestion.getMinute())
                .build();
    }
}
