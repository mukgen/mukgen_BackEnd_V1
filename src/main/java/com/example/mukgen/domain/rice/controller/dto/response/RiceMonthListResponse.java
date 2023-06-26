package com.example.mukgen.domain.rice.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RiceMonthListResponse {

    private List<RiceResponse> riceResponseList;

}
