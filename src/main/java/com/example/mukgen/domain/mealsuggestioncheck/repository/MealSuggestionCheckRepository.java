package com.example.mukgen.domain.mealsuggestioncheck.repository;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestioncheck.entity.MealSuggestionCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealSuggestionCheckRepository extends JpaRepository<MealSuggestionCheck, Long> {

    void removeByMealSuggestion(MealSuggestion mealSuggestion);
}
