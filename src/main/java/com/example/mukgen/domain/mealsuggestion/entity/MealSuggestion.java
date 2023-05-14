package com.example.mukgen.domain.mealsuggestion.entity;

import com.example.mukgen.domain.mealsuggestionlike.entity.MealSuggestionLike;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_meal_suggestion")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE tbl_meal_suggestion SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class MealSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;

    @Column(name = "create_at", nullable = false)
    private final LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "is_checked")
    private boolean isChecked = false;

    @OneToMany(mappedBy = "mealSuggestion", cascade = CascadeType.REMOVE)
    private List<MealSuggestionLike> mealSuggestionLikeList = new ArrayList<>();

    public void addLike() {
        this.likeCount++;
    }

    public void removeLike() {
        this.likeCount--;
    }

    public void clickCheck() {
        this.isChecked = !this.isChecked;
    }

    public void updateMealSuggestion(
            String content
    ) {
        this.content = content;
    }

    @Builder
    public MealSuggestion(
            String content,
            User user,
            List<MealSuggestionLike> mealSuggestionLikeList,
            int likeCount,
            boolean isDeleted,
            boolean isChecked
    ) {
        this.content = content;
        this.user = user;
        this.mealSuggestionLikeList = mealSuggestionLikeList;
        this.likeCount = likeCount;
        this.isDeleted = isDeleted;
        this.isChecked = isChecked;
    }
}