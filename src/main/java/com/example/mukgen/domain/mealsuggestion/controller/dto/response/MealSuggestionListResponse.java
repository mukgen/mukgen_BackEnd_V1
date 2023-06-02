package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MealSuggestionListResponse {

    private List<MealSuggestionResponse> mealSuggestionResponseList;
}
