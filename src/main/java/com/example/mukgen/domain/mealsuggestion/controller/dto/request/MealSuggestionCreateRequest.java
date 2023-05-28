package com.example.mukgen.domain.mealsuggestion.controller.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionCreateRequest {

    @Size(min = 1, max = 100, message = "급식 건의는 최소 1자, 최대 100자 입니다")
    private String content;
}