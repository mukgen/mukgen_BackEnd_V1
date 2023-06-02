package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.review.entity.ReviewComment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewCommentResponse {

    private Long reviewCommentId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ReviewCommentResponse of(
            ReviewComment reviewComment
    ) {
        return ReviewCommentResponse.builder()
                .reviewCommentId(reviewComment.getId())
                .content(reviewComment.getContent())
                .createdAt(reviewComment.getCreatedAt())
                .updatedAt(reviewComment.getUpdatedAt())
                .build();
    }
}
