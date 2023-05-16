package com.example.mukgen.domain.reviewcomment.repository;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.reviewcomment.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findAllByReview(Review review);
}
