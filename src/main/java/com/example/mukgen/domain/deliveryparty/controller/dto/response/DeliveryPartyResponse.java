package com.example.mukgen.domain.deliveryparty.controller.dto.response;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.user.controller.response.UserInfoResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DeliveryPartyResponse {

    private List<UserInfoResponse> userInfoResponseList;

    private String menu;

    private String place;

    private LocalDateTime meetTime;

    private Long deliveryPartyId;

    private Integer participantNumber;

    private Integer curParticipantNumber;

    public static DeliveryPartyResponse of(DeliveryParty deliveryParty){

        List<UserInfoResponse> userInfoResponses =
                deliveryParty.getUserList()
                        .stream()
                        .map(UserInfoResponse::of)
                        .toList();

        return DeliveryPartyResponse.builder()
                .participantNumber(deliveryParty.getParticipantNumber())
                .curParticipantNumber(deliveryParty.getUserList().size())
                .deliveryPartyId(deliveryParty.getId())
                .userInfoResponseList(userInfoResponses)
                .meetTime(deliveryParty.getMeetTime())
                .menu(deliveryParty.getMenu())
                .place(deliveryParty.getPlace())
                .build();
    }
}
