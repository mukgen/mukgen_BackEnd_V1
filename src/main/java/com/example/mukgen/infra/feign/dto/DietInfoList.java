package com.example.mukgen.infra.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DietInfoList {

    List<DietDTO> dietDTOList;
}
