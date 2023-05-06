package com.example.mukgen.domain.review.repository;

import com.example.mukgen.domain.meal.entity.Rice;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRice(Rice rice);

    boolean existsByRiceAndUser(Rice rice, User user);
}
