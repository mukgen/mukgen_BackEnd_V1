package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.repository.ReviewRepository;
import com.example.mukgen.domain.review.service.exception.ReviewNotFoundException;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCommentCreateRequest;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCommentUpdateRequest;
import com.example.mukgen.domain.review.controller.dto.response.ReviewCommentListResponse;
import com.example.mukgen.domain.review.controller.dto.response.ReviewCommentResponse;
import com.example.mukgen.domain.review.entity.ReviewComment;
import com.example.mukgen.domain.review.repository.ReviewCommentRepository;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.service.exception.NoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewCommentService {

    private final ReviewRepository reviewRepository;

    private final ReviewCommentRepository reviewCommentRepository;

    private final UserFacade userFacade;

    public void addReviewComment(
            ReviewCommentCreateRequest request,
            Long reviewId
    ) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        User user = userFacade.currentUser();

        if (user.getRole() != UserRole.CHEF)
            throw NoPermissionException.EXCEPTION;

        ReviewComment reviewComment = ReviewComment.builder()
                .isDeleted(false)
                .content(request.getContent())
                .review(review)
                .build();

        reviewCommentRepository.save(reviewComment);
    }

    public void modifyReviewComment(
            ReviewCommentUpdateRequest request,
            Long reviewId,
            Long reviewCommentId
    ) {
        reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        if (userFacade.currentUser().getRole() != UserRole.CHEF)
            throw NoPermissionException.EXCEPTION;

        reviewCommentRepository.findById(reviewCommentId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION)
                .updateReviewComment(request.getContent());
    }

    public void removeReviewComment(
            Long reviewId,
            Long reviewCommentId
    ) {
        reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        if (userFacade.currentUser().getRole() != UserRole.CHEF)
            throw NoPermissionException.EXCEPTION;

        reviewCommentRepository.deleteById(
                reviewCommentRepository.findById(reviewCommentId)
                        .orElseThrow(() -> ReviewNotFoundException.EXCEPTION)
                        .getId()
        );
    }

    @Transactional(readOnly = true)
    public ReviewCommentListResponse findReviewComment(
            Long reviewId
    ) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->ReviewNotFoundException.EXCEPTION);

        List<ReviewCommentResponse> reviewCommentResponseList =
                reviewCommentRepository.findAllByReview(review).stream()
                        .map(ReviewCommentResponse::of)
                        .toList();

        return ReviewCommentListResponse.builder()
                .reviewCommentResponseList(reviewCommentResponseList)
                .build();
    }
}
