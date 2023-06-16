package com.example.mukgen.domain.rice.controller.dto.request;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgenPickRequest {

    private int month;

    private int day;

    private RiceType riceType;

}
