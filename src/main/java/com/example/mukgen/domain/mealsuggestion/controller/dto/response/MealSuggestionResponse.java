package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MealSuggestionResponse {

    private String title;

    private String content;

    private String userName;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean deleted;
}
