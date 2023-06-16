package com.example.mukgen.domain.review.entity;

import com.example.mukgen.global.common.entity.BaseTimeEntity;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@SQLDelete(sql = "UPDATE `tbl_review` SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_review")
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

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

    public void modifyImageUrl(String url){
        this.imageUrl = url;
    }

}
