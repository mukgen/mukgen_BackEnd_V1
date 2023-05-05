package com.example.mukgen.domain.mealsuggestion.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MealSuggestionNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new MealSuggestionNotFoundException();

    public MealSuggestionNotFoundException() {
        super(ErrorCode.MEAL_SUGGESTION_NOT_FOUND);
    }
}
