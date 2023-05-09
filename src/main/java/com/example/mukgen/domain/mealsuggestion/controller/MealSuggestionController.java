package com.example.mukgen.domain.mealsuggestion.controller;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionMaximumResponse;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionMinimumResponse;
import com.example.mukgen.domain.mealsuggestion.service.MealSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mealSuggestion")
public class MealSuggestionController {

    private MealSuggestionService mealSuggestionService;

    @PostMapping
    public void createMealSuggestion(
            @RequestBody MealSuggestionCreateRequest request
    ) {
        mealSuggestionService.createMealSuggestion(request);
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

    @GetMapping("/{suggestionId}")
    public MealSuggestionMaximumResponse mealSuggestionDetails(
            @PathVariable Long suggestionId
    ) {
        return mealSuggestionService.findMealSuggestion(suggestionId);
    }

    @GetMapping("/list")
    public List<MealSuggestionMinimumResponse> mealSuggestionList() {
        return mealSuggestionService.findAllSuggestions();
    }
}