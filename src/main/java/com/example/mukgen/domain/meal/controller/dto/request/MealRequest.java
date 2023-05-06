package com.example.mukgen.domain.meal.controller.dto.request;

import com.example.mukgen.domain.meal.entity.RiceType;
import lombok.Data;

@Data
public class MealRequest {

    private RiceType riceType;
    private int year;
    private int month;

    private int day;
}
