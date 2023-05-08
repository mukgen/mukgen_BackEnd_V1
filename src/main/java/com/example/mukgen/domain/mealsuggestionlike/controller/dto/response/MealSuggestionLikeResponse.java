package com.example.mukgen.domain.mealsuggestionlike.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MealSuggestionLikeResponse {

    private Long mealSuggestionId;

    private String userName;
}
