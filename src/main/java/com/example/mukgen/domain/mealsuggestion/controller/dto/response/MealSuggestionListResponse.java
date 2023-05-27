package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class MealSuggestionListResponse {

    private List<MealSuggestionResponse> mealSuggestionResponseList;
}
