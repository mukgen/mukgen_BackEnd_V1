package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MealStatusResponse {

    private int totalCount;

    private LocalDateTime suggestionDate;

}
