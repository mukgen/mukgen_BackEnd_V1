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

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;

    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    @Column(name = "create_at", nullable = false)
    private final LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "is_updated")
    private boolean isUpdated = false;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "mealSuggestion", cascade = CascadeType.REMOVE)
    private List<MealSuggestionLike> mealSuggestionLikeList = new ArrayList<>();

    public void addLike() {
        this.likeCount++;
    }

    public void removeLike() {
        this.likeCount--;
    }

    public void addViewCount() {
        this.viewCount++;
    }

    public void updateMealSuggestion(
            String title,
            String content
    ) {
        this.title = title;
        this.content = content;
        this.updateAt = LocalDateTime.now();
        this.isUpdated = true;
    }

    @Builder
    public MealSuggestion(
            Long id,
            String title,
            String content,
            User user,
            List<MealSuggestionLike> mealSuggestionLikeList,
            int likeCount,
            int viewCount,
            LocalDateTime updateAt,
            boolean isUpdated,
            boolean isDeleted
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.mealSuggestionLikeList = mealSuggestionLikeList;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.updateAt = updateAt;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
    }
}