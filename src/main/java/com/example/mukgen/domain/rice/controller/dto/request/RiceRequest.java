package com.example.mukgen.domain.rice.controller.dto.request;

import com.example.mukgen.domain.rice.entity.RiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiceRequest {

    private RiceType riceType;

    private int year;

    private int month;

    private int day;

}
