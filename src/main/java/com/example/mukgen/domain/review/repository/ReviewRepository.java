package com.example.mukgen.domain.review.repository;

import com.example.mukgen.domain.meal.entity.Meal;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRice(Meal meal);

    boolean existsByRiceAndUser(Meal meal, User user);
}
