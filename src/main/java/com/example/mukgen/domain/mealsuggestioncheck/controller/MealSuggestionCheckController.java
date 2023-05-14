package com.example.mukgen.domain.mealsuggestioncheck.controller;

import com.example.mukgen.domain.mealsuggestioncheck.controller.dto.request.MealSuggestionCheckCreateRequest;
import com.example.mukgen.domain.mealsuggestioncheck.service.MealSuggestionCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mealSuggestion/check")
public class MealSuggestionCheckController {

    private final MealSuggestionCheckService mealSuggestionCheckService;

    @PostMapping
    public void clickCheck(
            @RequestBody
            MealSuggestionCheckCreateRequest mealSuggestionCheckCreateRequest
    ) {
        mealSuggestionCheckService.clickCheck(
                mealSuggestionCheckCreateRequest.getMealSuggestionId());
    }
}
