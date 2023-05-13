package com.example.mukgen.domain.mealsuggestionlike.controller.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeResponse {

    private Long mealSuggestionId;

    private String userName;

    @Builder
    public MealSuggestionLikeResponse(
            Long mealSuggestionId,
            String userName
    ) {
        this.mealSuggestionId = mealSuggestionId;
        this.userName = userName;
    }
}
