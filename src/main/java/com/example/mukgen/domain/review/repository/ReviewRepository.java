package com.example.mukgen.domain.review.repository;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findAllByRice(Rice rice);

    boolean existsByRiceAndUser(Rice rice, User user);

    List<Review> findAllByUser(User user);

    List<Review> findAllByCreatedAtGreaterThan(LocalDateTime dateTime, Sort sort);

    @Query(value = "SELECT r FROM tbl_review r WHERE r.month = :month AND r.day = :day")
    List<Review> findAllByMonthAndDay(@Param("month") int month, @Param("day") int day);

}
