package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestionlike.controller.dto.response.MealSuggestionLikeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MealSuggestionMaximumResponse {

    private String content;

    private String userName;

    private List<MealSuggestionLikeResponse> mealSuggestionLikeResponseList;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean isUpdated;

    public static MealSuggestionMaximumResponse of(MealSuggestion mealSuggestion) {
        List<MealSuggestionLikeResponse> mealSuggestionLikeResponses =
                mealSuggestion.getMealSuggestionLikeList().stream()
                        .map(it ->
                                MealSuggestionLikeResponse.builder()
                                        .mealSuggestionId(mealSuggestion.getId())
                                        .userName(mealSuggestion.getUser().getName())
                                        .build())
                        .toList();

        return MealSuggestionMaximumResponse.builder()
                .content(mealSuggestion.getContent())
                .userName(mealSuggestion.getUser().getName())
                .mealSuggestionLikeResponseList(mealSuggestionLikeResponses)
                .likeCount(mealSuggestion.getLikeCount())
                .viewCount(mealSuggestion.getViewCount())
                .createAt(mealSuggestion.getCreateAt())
                .updateAt(mealSuggestion.getUpdateAt())
                .isUpdated(mealSuggestion.isUpdated())
                .build();
    }
}
