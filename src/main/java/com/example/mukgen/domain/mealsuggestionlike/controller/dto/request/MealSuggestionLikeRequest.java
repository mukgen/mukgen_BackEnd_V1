package com.example.mukgen.domain.mealsuggestionlike.controller.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeRequest {

    @Column(name = "suggestion_id")
    private Long suggestionId;

}
