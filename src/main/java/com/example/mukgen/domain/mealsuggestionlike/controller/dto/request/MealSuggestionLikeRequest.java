package com.example.mukgen.domain.mealsuggestionlike.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeRequest {

    private Long suggestionId;

    public MealSuggestionLikeRequest(
            Long suggestionId
    ) {
        this.suggestionId = suggestionId;
    }
}
