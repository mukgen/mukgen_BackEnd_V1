package com.example.mukgen.domain.mealsuggestion.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Getter
@SQLDelete(sql = "UPDATE tbl_meal_suggestion SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_meal_suggestion")
public class MealSuggestion extends BaseTimeEntity {

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

    @Column(name = "hate_count", nullable = false)
    private int dislikeCount = 0;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "is_checked")
    private boolean isChecked = false;

    public void addLike() {
        this.likeCount++;
    }

    public void addDislike() {
        this.dislikeCount++;
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
            int likeCount,
            int dislikeCount,
            boolean isDeleted,
            boolean isChecked
    ) {
        this.content = content;
        this.user = user;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isDeleted = isDeleted;
        this.isChecked = isChecked;
    }
}