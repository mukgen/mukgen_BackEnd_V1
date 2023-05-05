package com.example.mukgen.domain.mealsuggestion.entity;

import com.example.mukgen.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MealSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateMealSuggestion(
            String title,
            String content
    ) {
        this.title = title;
        this.content = content;
        this.updateAt = LocalDateTime.now();
    }

    public void deleteMealSuggestion() {
        this.deleted = true;
    }

    public MealSuggestion(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.likeCount = 0;
        this.viewCount = 0;
        this.createAt = LocalDateTime.now();
        this.deleted = false;
    }

    public void addLike() {
        this.likeCount++;
    }
    public void removeLike() {
        this.likeCount--;
    }

    public void addViewCount() {
        this.viewCount++;
    }
}