package com.example.mukgen.domain.rice.entity;

import lombok.Getter;

@Getter
public enum RiceType {
    아침("조식"),
    점심("중식"),
    저녁("석식");

    private final String tag;

    RiceType(String tag) {
        this.tag = tag;
    }
}