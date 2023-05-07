package com.example.mukgen.domain.review.entity;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_review")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private int count;

    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rice_id")
    private Rice rice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
