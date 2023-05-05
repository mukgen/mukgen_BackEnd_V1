package com.example.mukgen.domain.mealsuggestion.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionUpdateRequest {

    private String title;

    private String content;

    public MealSuggestionUpdateRequest(
            String title,
            String content
    ) {
        this.title = title;
        this.content = content;
    }
}