package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionCreateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.request.MealSuggestionUpdateRequest;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealStatusListResponse;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealStatusResponse;
import com.example.mukgen.domain.mealsuggestion.controller.dto.response.MealSuggestionListResponse;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional()
@Service
public class MealSuggestionService {

    private final MealSuggestionRepository mealSuggestionRepository;

    private final UserFacade userFacade;

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

    public void removeMealSuggestion(
            Long suggestionId
    ) {
        MealSuggestion mealSuggestion = mealSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        if(mealSuggestion.getUser() != userFacade.currentUser())
            throw MealSuggestionWriterMissMatchException.EXCEPTION;

        mealSuggestionRepository.delete(mealSuggestion);
    }

    @Transactional(readOnly = true)
    public MealSuggestionListResponse findAllSuggestion(
    ) {
        return MealSuggestionListResponse.builder()
                .mealSuggestionResponseList(
                        mealSuggestionRepository.findAll()
                        .stream()
                        .map(MealSuggestionResponse::of)
                        .toList())
                .build();
    }

    public void clickCheck(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        User user = userFacade.currentUser();

        if (user.getRole() != UserRole.TEACHER)
            throw NoPermissionException.EXCEPTION;

        mealSuggestion.clickCheck();
    }

    public void addLike(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.addLike();
    }

    public void addDislike(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionNotFoundException.EXCEPTION);

        mealSuggestion.addDislike();
    }

    public MealStatusListResponse getMealStatusList(){

        LocalDateTime now = LocalDateTime.now().minusDays(6);

        List<MealStatusResponse> mealStatusResponseList = new ArrayList<>();

        for(int i=0;i<7;i++){
            List<MealSuggestion> mealSuggestionList = mealSuggestionRepository.findAllByMonthAndDay(now.getMonthValue(), now.getDayOfMonth());
            MealStatusResponse mealStatusResponse = MealStatusResponse.builder()
                    .totalCount(mealSuggestionList.size())
                    .month(now.getMonthValue())
                    .day(now.getDayOfMonth())
                    .build();
            mealStatusResponseList.add(mealStatusResponse);
            now = now.plusDays(1);
        }

        return MealStatusListResponse.builder()
                .mealStatusResponseList(mealStatusResponseList)
                .build();
    }
}