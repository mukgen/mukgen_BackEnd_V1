package com.example.mukgen.domain.mealsuggestion.controller;

import com.example.mukgen.domain.mealsuggestion.service.MealSuggestionLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mealSuggestion/like")
@RequiredArgsConstructor
public class MealSuggestionLikeController {

    private final MealSuggestionLikeService mealSuggestionLikeService;

    @PostMapping("/{suggestionId}")
    public void clickLike(
            @PathVariable Long suggestionId
    ) {
        mealSuggestionLikeService.clickLike(suggestionId);
    }
}
