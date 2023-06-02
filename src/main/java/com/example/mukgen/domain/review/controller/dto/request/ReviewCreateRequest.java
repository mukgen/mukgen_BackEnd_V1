package com.example.mukgen.domain.review.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewCreateRequest {

    @Min(value = 1, message = "평점은 최소 1점입니다")
    @Max(value = 5, message = "평점은 최대 5점입니다.")
    private int count;

    @Size(min = 10, max = 100, message = "리뷰는 최소 10자, 최대 100자 입니다")
    private String review;

}
