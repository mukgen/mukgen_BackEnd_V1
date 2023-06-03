package com.example.mukgen.domain.rice.controller.dto.response;

import com.example.mukgen.domain.rice.entity.MukgenPick;
import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MukgenPickResponse {

    private int month;

    private int day;

    private RiceType riceType;

    private int riceId;

    public static MukgenPickResponse of(MukgenPick mukgenPick){

        return MukgenPickResponse.builder()
                .riceId(mukgenPick.getRiceId())
                .riceType(mukgenPick.getRiceType())
                .month(mukgenPick.getMonth())
                .day(mukgenPick.getDay())
                .build();
    }
}
