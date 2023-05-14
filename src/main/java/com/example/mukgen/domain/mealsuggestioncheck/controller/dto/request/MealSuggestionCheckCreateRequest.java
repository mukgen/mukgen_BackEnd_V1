package com.example.mukgen.domain.mealsuggestioncheck.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionCheckCreateRequest {

    private Long mealSuggestionId;
}
