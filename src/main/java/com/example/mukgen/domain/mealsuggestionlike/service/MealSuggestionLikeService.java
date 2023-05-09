package com.example.mukgen.domain.mealsuggestionlike.service;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.mealsuggestionlike.entity.MealSuggestionLike;
import com.example.mukgen.domain.mealsuggestionlike.repositery.MealSuggestionLikeRepository;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealSuggestionLikeService {

    private final MealSuggestionLikeRepository mealSuggestionLikeRepository;

    private final MealSuggestionRepository mealSuggestionRepository;

    private final UserFacade userFacade;

    public void clickLike(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        String userName = userFacade.currentUser().getName();

        if (mealSuggestionLikeRepository.existsByMealSuggestionAndUserName(mealSuggestion, userName)) {
            mealSuggestion.removeLike(userName);
            mealSuggestionLikeRepository.save(
                    MealSuggestionLike.builder()
                            .mealSuggestion(mealSuggestion)
                            .userName(userName)
                            .build());
        }
        else {
            mealSuggestion.addLike(userName);
            mealSuggestionLikeRepository.removeByMealSuggestionAndUserName(mealSuggestion, userName);
        }
    }
}
