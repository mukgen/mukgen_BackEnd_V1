package com.example.mukgen.domain.deliveryparty.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeliveryPartyListResponse {

    private List<DeliveryPartyResponse> deliveryPartyResponseList;
}
