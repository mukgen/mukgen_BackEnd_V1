package com.example.mukgen.domain.rice.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiceResponse {

    private String riceType;

    private String item;

    private int riceId;
}
