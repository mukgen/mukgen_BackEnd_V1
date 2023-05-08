package com.example.mukgen.domain.mealsuggestionlike.entity;

import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity(name = "tbl_mealSuggestionLikes")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MealSuggestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "suggestion_id")
    private MealSuggestion mealSuggestion;

    public MealSuggestionLike(
            MealSuggestion mealSuggestion,
            String userName
    ) {
        this.mealSuggestion = mealSuggestion;
        this.userName = userName;
    }
}
