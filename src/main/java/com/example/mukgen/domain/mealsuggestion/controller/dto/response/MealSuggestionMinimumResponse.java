package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestionlike.controller.dto.response.MealSuggestionLikeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MealSuggestionMinimumResponse {

    private String userName;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean isUpdated;

    public static MealSuggestionMinimumResponse of(MealSuggestion mealSuggestion) {
        return MealSuggestionMinimumResponse.builder()
                .userName(mealSuggestion.getUser().getName())
                .likeCount(mealSuggestion.getLikeCount())
                .viewCount(mealSuggestion.getViewCount())
                .createAt(mealSuggestion.getCreateAt())
                .updateAt(mealSuggestion.getUpdateAt())
                .isUpdated(mealSuggestion.isUpdated())
                .build();
    }
}
