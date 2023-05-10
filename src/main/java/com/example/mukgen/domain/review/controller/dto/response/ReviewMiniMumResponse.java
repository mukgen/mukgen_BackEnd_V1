package com.example.mukgen.domain.review.controller.dto.response;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewMiniMumResponse {

    private Integer count;

    private String userName;

    private RiceType riceType;

    private Integer year;

    private Integer month;

    private Integer day;

}
