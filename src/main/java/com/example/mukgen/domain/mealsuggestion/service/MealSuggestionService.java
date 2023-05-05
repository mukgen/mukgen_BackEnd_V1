package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionResponse;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealSuggestionService {

    private final MealSuggestionRepository mealSuggestionRepository;

    private final UserFacade userFacade;

    @Transactional
    public void createMealSuggestion(
            MealSuggestionCreateRequest request
    ) {
        User curUser = userFacade.currentUser();

        mealSuggestionRepository.save(
                new MealSuggestion(request.getTitle(), request.getContent(), curUser)
        );
    }

    public MealSuggestionResponse findOneSuggestion(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        return MealSuggestionResponse.builder()
                .title(mealSuggestion.getTitle())
                .content(mealSuggestion.getContent())
                .userName(mealSuggestion.getUser().getName())
                .likeCount(mealSuggestion.getLikeCount())
                .viewCount(mealSuggestion.getViewCount())
                .createAt(mealSuggestion.getCreateAt())
                .updateAt(mealSuggestion.getUpdateAt())
                .build();
    }

    @Transactional
    public void updateMealSuggestion(
            MealSuggestionUpdateRequest request,
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.updateMealSuggestion(request.getTitle(), request.getContent());
    }

    @Transactional
    public void deleteMealSuggestion(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.deleteMealSuggestion();
    }
}