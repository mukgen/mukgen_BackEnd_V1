package com.example.mukgen.domain.rice.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RiceTodayResponse {

    private List<RiceResponse> responseList;

}
