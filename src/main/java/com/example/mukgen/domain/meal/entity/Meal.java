package com.example.mukgen.domain.meal.entity;

import com.example.mukgen.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Meal {

    @Id
    private int id;
    private String item;

    @OneToMany(mappedBy = "meal")
    private List<Review> reviewList = new ArrayList<>();

    public Meal(String[] item, int id) {
        this.item = Arrays.toString(item);
        this.id = id;
    }
    public Meal(int id) {
        this.item = "등록된 급식이 없습니다.";
        this.id = id;
    }

}