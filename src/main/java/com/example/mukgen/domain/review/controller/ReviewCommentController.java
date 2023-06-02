package com.example.mukgen.domain.review.controller;

import com.example.mukgen.domain.review.controller.dto.request.ReviewCommentCreateRequest;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCommentUpdateRequest;
import com.example.mukgen.domain.review.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/review-comment")
@RestController
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;

    @PostMapping("/{reviewId}")
    public void reviewCommentAdd(
            @RequestBody @Valid ReviewCommentCreateRequest request,
            @PathVariable Long reviewId
            ) {
        reviewCommentService.addReviewComment(request, reviewId);
    }

    @PutMapping("/{reviewId}/{reviewCommentId}")
    public void reviewCommentModify(
            @RequestBody @Valid ReviewCommentUpdateRequest request,
            @PathVariable Long reviewId,
            @PathVariable Long reviewCommentId
            ) {
        reviewCommentService.modifyReviewComment(request, reviewId, reviewCommentId);
    }

    @DeleteMapping("/{reviewId}/{reviewCommentId}")
    public void reviewCommentRemove(
            @PathVariable Long reviewId,
            @PathVariable Long reviewCommentId
    ) {
        reviewCommentService.removeReviewComment(reviewId, reviewCommentId);
    }
}
