package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MealStatusResponse {

    private int totalCount;

    private int month;

    private int day;

}
