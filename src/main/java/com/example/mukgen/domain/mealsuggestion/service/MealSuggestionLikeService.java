package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestionLike;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionLikeRepository;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealSuggestionLikeService {

    private final MealSuggestionLikeRepository mealSuggestionLikeRepository;

    private final MealSuggestionRepository mealSuggestionRepository;

    private final UserFacade userFacade;

    @Transactional
    public void clickLike(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        String userName = userFacade.currentUser().getName();

        if (mealSuggestionLikeRepository.existsByMealSuggestionAndUserName(mealSuggestion, userName)) {
            mealSuggestion.removeLike();
            mealSuggestionLikeRepository.removeByMealSuggestionAndUserName(mealSuggestion, userName);
        }
        else {
            mealSuggestion.addLike();
            mealSuggestionLikeRepository.save(
                    MealSuggestionLike.builder()
                            .mealSuggestion(mealSuggestion)
                            .userName(userName)
                            .build());
        }
    }
}
