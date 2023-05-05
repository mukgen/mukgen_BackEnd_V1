package com.example.mukgen.domain.mealsuggestion.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionDeleteRequest {

    private Long suggestionId;

    public MealSuggestionDeleteRequest(
            Long suggestionId
    ) {
        this.suggestionId = suggestionId;
    }
}