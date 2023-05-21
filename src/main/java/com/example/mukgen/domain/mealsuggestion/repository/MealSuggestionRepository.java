package com.example.mukgen.domain.mealsuggestion.repository;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MealSuggestionRepository extends JpaRepository<MealSuggestion, Long> {

    void removeByCreatedAtEquals(LocalDateTime time);
}