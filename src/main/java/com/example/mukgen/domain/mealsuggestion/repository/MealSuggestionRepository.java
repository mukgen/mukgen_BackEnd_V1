package com.example.mukgen.domain.mealsuggestion.repository;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealSuggestionRepository extends JpaRepository<MealSuggestion, Long> {
}
