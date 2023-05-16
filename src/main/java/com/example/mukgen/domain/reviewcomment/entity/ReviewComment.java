package com.example.mukgen.domain.reviewcomment.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "tbl_review_comment")
@Getter
@Builder
@SQLDelete(sql = "UPDATE tbl_review_comment SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;

    public void updateReviewComment(
            String content
    ) {
       this.content = content;
    }

}
