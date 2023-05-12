package com.example.mukgen.domain.mealsuggestionlike.controller.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;

import javax.persistence.Column;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeResponse {

    @Column(name = "suggestion_id")
    private Long mealSuggestionId;

    @Column(name = "user_name")
    private String userName;

    @Builder
    public MealSuggestionLikeResponse(
            Long mealSuggestionId,
            String userName
    ) {
        this.mealSuggestionId = mealSuggestionId;
        this.userName = userName;
    }
}
