package com.example.mukgen.domain.review.repository;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByReview(Review review);
}
