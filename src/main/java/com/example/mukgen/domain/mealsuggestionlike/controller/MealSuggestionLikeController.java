package com.example.mukgen.domain.mealsuggestionlike.controller;

import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestionlike.service.MealSuggestionLikeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionLikeController {

    private MealSuggestionLikeService mealSuggestionLikeService;

    @PostMapping("/{suggestionId}")
    public void clickLike(
            @PathVariable Long suggestionId
    ) {
        mealSuggestionLikeService.clickLike(suggestionId);
    }
}
