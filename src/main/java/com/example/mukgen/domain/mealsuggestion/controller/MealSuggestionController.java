package com.example.mukgen.domain.mealsuggestion.controller;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionResponse;
import com.example.mukgen.domain.mealsuggestion.service.MealSuggestionService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MealSuggestionController {

    private MealSuggestionService mealSuggestionService;

    @PostMapping
    public void createMealSuggestion(
            @RequestBody MealSuggestionCreateRequest request
    ) {
        mealSuggestionService.createMealSuggestion(request);
    }

    /* @GetMapping("/list")
    public MealSuggestionResponse findAllSuggestion() {
        return mealSuggestionService.findAllSuggestion;
    } */

    @GetMapping("/{suggestionId}")
    public MealSuggestionResponse findOneSuggestion(
            @PathVariable Long suggestionId
    ) {
        return mealSuggestionService.findOneSuggestion(suggestionId);
    }

    @PutMapping("/{suggestionId}")
    public void updateMealSuggestion(
            @RequestBody MealSuggestionUpdateRequest request,
            @PathVariable Long suggestionId
    ) {
        mealSuggestionService.updateMealSuggestion(request, suggestionId);
    }

    @DeleteMapping("/{suggestionId}")
    public void deleteMealSuggestion(
            @PathVariable Long suggestionId
    ) {
        mealSuggestionService.deleteMealSuggestion(suggestionId);
    }


}