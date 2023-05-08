package com.example.mukgen.domain.mealsuggestionlike.service;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.mealsuggestionlike.entity.MealSuggestionLike;
import com.example.mukgen.domain.mealsuggestionlike.repositery.MealSuggestionLikeRepository;
import com.example.mukgen.domain.user.entity.User;
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

        User curUser = userFacade.currentUser();

        if (mealSuggestionLikeRepository.existsByMealSuggestionAndUserName(mealSuggestion, curUser.getName())) {
            mealSuggestion.removeLike(curUser.getName());
            mealSuggestionLikeRepository.removeByMealSuggestionAndUserName(mealSuggestion, curUser.getName());
        }
        else {
            mealSuggestion.addLike(curUser.getName());
            MealSuggestionLike mealSuggestionLike = new MealSuggestionLike(mealSuggestion, curUser.getName());
            mealSuggestionLikeRepository.save(mealSuggestionLike);
        }
    }
}
