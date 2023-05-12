package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionMaximumResponse;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionMinimumResponse;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionWriterMissMatchException;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealSuggestionService {

    private final MealSuggestionRepository mealSuggestionRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addMealSuggestion(
            MealSuggestionCreateRequest request
    ) {
        User user = userFacade.currentUser();

        mealSuggestionRepository.save(
                MealSuggestion.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .user(user)
                        .build()
        );
    }

    @Transactional
    public void modifyMealSuggestion(
            MealSuggestionUpdateRequest request,
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        if(mealSuggestion.getUser() != userFacade.currentUser())
            throw MealSuggestionWriterMissMatchException.EXCEPTION;

        mealSuggestion.updateMealSuggestion(request.getTitle(), request.getContent());
    }

    @Transactional
    public void removeMealSuggestion(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        if(mealSuggestion.getUser() != userFacade.currentUser())
            throw MealSuggestionWriterMissMatchException.EXCEPTION;

        mealSuggestionRepository.delete(mealSuggestion);
    }

    @Transactional
    public MealSuggestionMaximumResponse findMealSuggestion(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.addViewCount();

        return MealSuggestionMaximumResponse.of(mealSuggestion);
    }

    public List<MealSuggestionMinimumResponse> findAllSuggestion(
    ) {
        return mealSuggestionRepository.findAll()
                .stream()
                .map(MealSuggestionMinimumResponse::of)
                .toList();
    }
}