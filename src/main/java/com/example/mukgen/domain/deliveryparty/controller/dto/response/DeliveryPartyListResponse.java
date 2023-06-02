package com.example.mukgen.domain.deliveryparty.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DeliveryPartyListResponse {

    private List<DeliveryPartyResponse> deliveryPartyResponseList;

}
