package com.example.mukgen.domain.rice.controller.dto.response;

import com.example.mukgen.domain.rice.entity.MukgenPick;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MukgenPickResponse {

    private int month;

    private int day;

    public static MukgenPickResponse of(MukgenPick mukgenPick){

        return MukgenPickResponse.builder()
                .month(mukgenPick.getMonth())
                .day(mukgenPick.getDay())
                .build();
    }
}
