package com.example.mukgen.domain.rice.entity;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.global.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_rice")
public class Rice extends BaseTimeEntity {

    @Id
    private int id;

    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "rice_type", nullable = true)
    private RiceType riceType;

    @Column(name = "month", nullable = false)
    private int month;

    @OneToMany(mappedBy = "rice")
    private List<Review> reviewList = new ArrayList<>();

    public Rice(String[] item, int id, RiceType riceType) {
        this.riceType = riceType;
        this.item = Arrays.toString(item);
        this.id = id;
    }
    public Rice(int id) {
        this.item = "등록된 급식이 없습니다.";
        this.riceType = RiceType.NONE;
        this.month = LocalDate.now().getMonthValue();
        this.id = id;
    }

}