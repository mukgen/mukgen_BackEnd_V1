package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MealSuggestionResponse {

    private Long id;

    private String content;

    private String userName;

    private int likeCount;

    private int hateCount;

    private LocalDateTime createdAt;

    private boolean isChecked;

    public static MealSuggestionResponse of(MealSuggestion mealSuggestion) {

        return MealSuggestionResponse.builder()
                .id(mealSuggestion.getId())
                .content(mealSuggestion.getContent())
                .userName(mealSuggestion.getUser().getName())
                .likeCount(mealSuggestion.getLikeCount())
                .hateCount(mealSuggestion.getHateCount())
                .createdAt(mealSuggestion.getCreatedAt())
                .isChecked(mealSuggestion.isChecked())
                .build();
    }
}
