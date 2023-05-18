package com.example.mukgen.domain.review.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCommentCreateRequest {

    @Size(min = 1, max = 100, message = "리뷰 댓글은 최소 1자, 최대 100자입니 다.")
    private String content;
}
