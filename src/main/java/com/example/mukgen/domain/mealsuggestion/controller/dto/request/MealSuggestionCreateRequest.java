package com.example.mukgen.domain.mealsuggestion.controller.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionCreateRequest {

    private String title;

    private String content;

    @Builder
    public MealSuggestionCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}