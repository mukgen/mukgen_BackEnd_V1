package com.example.mukgen.domain.deliveryparty.controller.dto.response;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DeliveryPartyResponse {

    private String username;

    private String menu;

    private String place;

    private LocalDateTime meetTime;

    public static DeliveryPartyResponse of(DeliveryParty deliveryParty){

        return DeliveryPartyResponse.builder()
                .meetTime(deliveryParty.getMeetTime())
                .menu(deliveryParty.getMenu())
                .place(deliveryParty.getPlace())
                .username(deliveryParty.getUser().getName())
                .build();
    }
}
