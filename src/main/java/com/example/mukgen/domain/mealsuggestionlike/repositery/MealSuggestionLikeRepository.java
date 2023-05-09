package com.example.mukgen.domain.mealsuggestionlike.repositery;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.mealsuggestionlike.entity.MealSuggestionLike;
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
