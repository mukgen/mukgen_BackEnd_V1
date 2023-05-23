package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionResponse;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionNotFoundException;
import com.example.mukgen.domain.mealsuggestion.service.exception.MealSuggestionWriterMissMatchException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.service.exception.NoPermissionException;
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

        mealSuggestion.updateMealSuggestion(request.getContent());
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

    public List<MealSuggestionResponse> findAllSuggestion(
    ) {
        return mealSuggestionRepository.findAll()
                .stream()
                .map(MealSuggestionResponse::of)
                .toList();
    }

    @Transactional
    public void clickCheck(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        User user = userFacade.currentUser();

        if (user.getRole() != UserRole.CHEF)
            throw NoPermissionException.EXCEPTION;

        mealSuggestion.clickCheck();
    }

    @Transactional
    public void addLike(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.addLike();
    }

    @Transactional
    public void addDislike(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.addDislike();
    }
}