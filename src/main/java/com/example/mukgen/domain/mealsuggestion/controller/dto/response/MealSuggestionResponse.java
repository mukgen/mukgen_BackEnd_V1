package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MealSuggestionResponse {

    private Long id;

    private String content;

    private int likeCount;

    private int dislikeCount;

    private boolean isChecked;

    private LocalDateTime createdAt;

    public static MealSuggestionResponse of(MealSuggestion mealSuggestion) {

        return MealSuggestionResponse.builder()
                .id(mealSuggestion.getId())
                .content(mealSuggestion.getContent())
                .likeCount(mealSuggestion.getLikeCount())
                .dislikeCount(mealSuggestion.getDislikeCount())
                .isChecked(mealSuggestion.isChecked())
                .createdAt(mealSuggestion.getCreatedAt())
                .build();
    }
}
