package com.example.mukgen.domain.review.entity.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private int count;

    private String content;

    private String userName;
}
