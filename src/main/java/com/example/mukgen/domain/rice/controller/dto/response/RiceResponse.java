package com.example.mukgen.domain.rice.controller.dto.response;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiceResponse {

    private RiceType riceType;

    private String item;

    private int riceId;
}
