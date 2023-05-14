package com.example.mukgen.domain.mealsuggestioncheck.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MealSuggestionCheckNoPermissionException extends BusinessException {

    public static final BusinessException EXCEPTION =
            new MealSuggestionCheckNoPermissionException();

    public MealSuggestionCheckNoPermissionException() {
        super(ErrorCode.MEAL_SUGGESTION_CHECK_NO_PERMISSION);
    }
}
