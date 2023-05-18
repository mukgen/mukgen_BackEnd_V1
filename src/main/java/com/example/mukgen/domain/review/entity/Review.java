package com.example.mukgen.domain.review.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_review")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE `tbl_review` SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private int count;

    private String review;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rice_id")
    private Rice rice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewComment> reviewCommentList = new ArrayList<>();

}
