package com.example.mukgen.domain.deliveryparty.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryPartyRequest {

    @Size(min = 2, max = 20)
    private String menu;

    @Min(2)
    @Max(10)
    private Integer participantNumber;

    @Size(min = 2, max = 20)
    private String place;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime meetTime;
}
