package com.example.mukgen.domain.meal.entity;

import lombok.Getter;

@Getter
public enum MealType {
    BREAKFAST("조식"),
    LUNCH("중식"),
    DINNER("석식");

    private final String tag;

    MealType(String tag) {
        this.tag = tag;
    }
}