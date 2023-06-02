package com.example.mukgen.domain.review.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Getter
@Builder
@SQLDelete(sql = "UPDATE tbl_review_comment SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "tbl_review_comment")
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
