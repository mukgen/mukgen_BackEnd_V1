package com.example.mukgen.domain.meal.controller.dto.request;

import com.example.mukgen.domain.meal.entity.MealType;
import lombok.Data;

@Data
public class MealRequest {

    private MealType mealType;
    private int year;
    private int month;

    private int day;
}
