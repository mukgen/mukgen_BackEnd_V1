package com.example.mukgen.domain.deliveryparty.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryPartyRequest {

    @Size(min = 2, max = 20, message = "메뉴는 최소 2자, 최대 20자입니다. ")
    private String menu;

    @Min(value = 2, message = "인원은 최소 2명입니다.")
    @Max(value = 10, message = "인원은 최대 10명입니다.")
    private Integer participantNumber;

    @Size(min = 2, max = 20, message = "장소는 최소 2자, 최대 20자입니다.")
    private String place;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime meetTime;
}
