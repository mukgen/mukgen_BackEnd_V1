package com.example.mukgen.domain.review.entity.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ReviewResponseList {

    List<ReviewResponse> reviewResponseList;
}
