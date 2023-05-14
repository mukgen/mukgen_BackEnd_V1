package com.example.mukgen.domain.mealsuggestioncheck.entity;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_meal_suggestion_check")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealSuggestionCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "meal_suggestion_id")
    private MealSuggestion mealSuggestion;

    @Builder
    public MealSuggestionCheck(
            MealSuggestion mealSuggestion
    ) {
        this.mealSuggestion = mealSuggestion;
    }
}
