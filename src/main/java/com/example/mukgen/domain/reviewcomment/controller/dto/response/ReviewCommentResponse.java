package com.example.mukgen.domain.reviewcomment.controller.dto.response;

import com.example.mukgen.domain.reviewcomment.entity.ReviewComment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
