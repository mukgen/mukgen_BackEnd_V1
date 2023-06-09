package com.example.mukgen.domain.rice.controller.dto.request;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RiceRequest {

    private RiceType riceType;

    private int year;

    private int month;

    private int day;

}
