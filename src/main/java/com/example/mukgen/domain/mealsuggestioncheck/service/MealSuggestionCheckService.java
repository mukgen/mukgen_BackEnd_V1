package com.example.mukgen.domain.mealsuggestioncheck.service;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import com.example.mukgen.domain.mealsuggestioncheck.entity.MealSuggestionCheck;
import com.example.mukgen.domain.mealsuggestioncheck.repository.MealSuggestionCheckRepository;
import com.example.mukgen.domain.mealsuggestioncheck.service.exception.MealSuggestionCheckNoPermissionException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealSuggestionCheckService {


    private final UserFacade userFacade;

    private final MealSuggestionRepository mealSuggestionRepository;

    private final MealSuggestionCheckRepository mealSuggestionCheckRepository;

    @Transactional
    public void clickCheck(
            Long mealSuggestionId
    ) {
        MealSuggestion mealSuggestion =
                mealSuggestionRepository.findById(mealSuggestionId)
                        .orElseThrow(() -> MealSuggestionCheckNoPermissionException.EXCEPTION);

        User user = userFacade.currentUser();

        if (user.getRole() != UserRole.CHEF) {
            throw MealSuggestionCheckNoPermissionException.EXCEPTION;
        }

        if (mealSuggestion.isChecked()) {
            mealSuggestion.setChecked(false);
            mealSuggestionCheckRepository.removeByMealSuggestion(mealSuggestion);
        }
        else {
            mealSuggestion.setChecked(true);
            mealSuggestionCheckRepository.save(
                    MealSuggestionCheck.builder()
                            .mealSuggestion(mealSuggestion)
                            .build());
        }
    }
}
