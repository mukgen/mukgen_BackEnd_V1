package com.example.mukgen.domain.mealsuggestion.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeRequest {

    private Long suggestionId;
}
