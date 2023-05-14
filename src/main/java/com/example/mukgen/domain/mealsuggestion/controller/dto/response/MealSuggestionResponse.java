package com.example.mukgen.domain.mealsuggestion.controller.dto.response;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestionlike.controller.dto.response.MealSuggestionLikeResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MealSuggestionResponse {


    private Long id;

    private String content;

    private String userName;

    private List<MealSuggestionLikeResponse> mealSuggestionLikeResponseList;

    private int likeCount;

    private LocalDateTime createAt;

    private boolean isChecked;

    public static MealSuggestionResponse of(MealSuggestion mealSuggestion) {
        List<MealSuggestionLikeResponse> mealSuggestionLikeResponses =
                mealSuggestion.getMealSuggestionLikeList().stream()
                        .map(it ->
                                MealSuggestionLikeResponse.builder()
                                        .mealSuggestionId(mealSuggestion.getId())
                                        .userName(mealSuggestion.getUser().getName())
                                        .build())
                        .toList();

        return MealSuggestionResponse.builder()
                .id(mealSuggestion.getId())
                .content(mealSuggestion.getContent())
                .userName(mealSuggestion.getUser().getName())
                .mealSuggestionLikeResponseList(mealSuggestionLikeResponses)
                .likeCount(mealSuggestion.getLikeCount())
                .createAt(mealSuggestion.getCreateAt())
                .isChecked(mealSuggestion.isChecked())
                .build();
    }
}
