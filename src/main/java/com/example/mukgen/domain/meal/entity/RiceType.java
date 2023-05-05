package com.example.mukgen.domain.meal.entity;

import lombok.Getter;

@Getter
public enum RiceType {
    BREAKFAST("조식"),
    LUNCH("중식"),
    DINNER("석식");

    private final String tag;

    RiceType(String tag) {
        this.tag = tag;
    }
}