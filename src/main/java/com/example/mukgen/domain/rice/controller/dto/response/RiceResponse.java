package com.example.mukgen.domain.rice.controller.dto.response;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RiceResponse {

    private RiceType riceType;

    private String item;

    private int riceId;

    public static RiceResponse of(Rice rice){
        return RiceResponse.builder()
                .riceId(rice.getId())
                .item(rice.getItem())
                .riceType(rice.getRiceType())
                .build();
    }
}
