package com.example.mukgen.domain.mealsuggestion.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MealSuggestionDeletedException extends BusinessException {
    public static BusinessException EXCEPTION = new MealSuggestionDeletedException();

    public MealSuggestionDeletedException() {
        super(ErrorCode.MEAL_SUGGESTION_DELETED);
    }
}
