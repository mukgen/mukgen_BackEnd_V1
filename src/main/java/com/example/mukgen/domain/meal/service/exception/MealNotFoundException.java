package com.example.mukgen.domain.meal.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class MealNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new MealNotFoundException();

    public MealNotFoundException() {
        super(ErrorCode.MEAL_NOT_FOUND);
    }
}
