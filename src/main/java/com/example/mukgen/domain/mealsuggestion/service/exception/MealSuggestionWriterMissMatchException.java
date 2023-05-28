package com.example.mukgen.domain.mealsuggestion.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MealSuggestionWriterMissMatchException extends BusinessException {
    public static BusinessException EXCEPTION = new MealSuggestionWriterMissMatchException();

    public MealSuggestionWriterMissMatchException() {
        super(ErrorCode.MEAL_SUGGESTION_WRITER_MISMATCH);
    }
}
