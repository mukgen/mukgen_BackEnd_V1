package com.example.mukgen.domain.mealsuggestion.controller;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionListResponse;
import com.example.mukgen.domain.mealsuggestion.service.MealSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/meal-suggestion")
@RestController
public class MealSuggestionController {

    private final MealSuggestionService mealSuggestionService;

    @PostMapping
    public void mealSuggestionAdd(
            @RequestBody @Valid MealSuggestionCreateRequest request
    ) {
        mealSuggestionService.addMealSuggestion(request);
    }

    @PutMapping("/{suggestionId}")
    public void mealSuggestionModify(
            @RequestBody @Valid MealSuggestionUpdateRequest request,
            @PathVariable Long suggestionId
    ) {
        mealSuggestionService.modifyMealSuggestion(request, suggestionId);
    }

    @DeleteMapping("/{suggestionId}")
    public void mealSuggestionRemove(
            @PathVariable Long suggestionId
    ) {
        mealSuggestionService.removeMealSuggestion(suggestionId);
    }

    @GetMapping("/list")
    public MealSuggestionListResponse mealSuggestionList() {
        return mealSuggestionService.findAllSuggestion();
    }

    @PostMapping("/check/{mealSuggestionId}")
    public void checkClick(
            @PathVariable Long mealSuggestionId
    ) {
        mealSuggestionService.clickCheck(mealSuggestionId);
    }

    @PostMapping("/like/{mealSuggestionId}")
    public void likeAdd(
            @PathVariable Long mealSuggestionId
    ) {
        mealSuggestionService.addLike(mealSuggestionId);
    }

    @PostMapping("/hate/{mealSuggestionId}")
    public void dislikeAdd(
            @PathVariable Long mealSuggestionId
    ) {
        mealSuggestionService.addDislike(mealSuggestionId);
    }
}