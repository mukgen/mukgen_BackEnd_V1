package com.example.mukgen.domain.deliveryparty.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryPartyRequest {

    private String menu;

    private Integer participantNumber;

    private String place;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime meetTime;
}
