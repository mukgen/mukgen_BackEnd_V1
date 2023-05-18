package com.example.mukgen.domain.mealsuggestion.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.mealsuggestion.entity.MealSuggestion;
import lombok.*;

import javax.persistence.*;

@Entity(name = "tbl_suggestion_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MealSuggestionLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "suggestion_id")
    private MealSuggestion mealSuggestion;

    @Builder
    public MealSuggestionLike(
            MealSuggestion mealSuggestion,
            String userName
    ) {
        this.mealSuggestion = mealSuggestion;
        this.userName = userName;
    }
}
