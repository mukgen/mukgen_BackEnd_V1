package com.example.mukgen.domain.mealsuggestion.repository;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealSuggestionLikeRepository extends JpaRepository<MealSuggestionLike, Long> {

    boolean existsByMealSuggestionAndUserName(
            MealSuggestion mealSuggestion,
            String userName
    );

    void removeByMealSuggestionAndUserName(
            MealSuggestion mealSuggestion,
            String UserName
    );
}
