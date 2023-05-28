package com.example.mukgen.domain.rice.entity;

import lombok.Getter;

@Getter
public enum RiceType {
    BREAKFAST("아침", "조식"),
    LUNCH("점심", "중식"),
    DINNER("저녁", "석식");

    private final String tag;

    private final String realTag;

    RiceType(String tag, String realTag) {
        this.tag = tag;
        this.realTag = realTag;
    }
}