package com.example.mukgen.domain.mealsuggestion.repository;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealSuggestionRepository extends JpaRepository<MealSuggestion, Long> {

    void removeAllByCreatedAtBefore(LocalDateTime time);

    @Query(value = "SELECT m FROM tbl_meal_suggestion m WHERE m.month = :month AND m.day = :day")
    List<MealSuggestion> findAllByMonthAndDay(@Param("month") int month, @Param("day") int day);
}