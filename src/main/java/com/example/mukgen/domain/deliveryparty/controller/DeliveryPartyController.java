package com.example.mukgen.domain.deliveryparty.controller;

import com.example.mukgen.domain.deliveryparty.controller.dto.request.DeliveryPartyRequest;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyListResponse;
import com.example.mukgen.domain.deliveryparty.service.DeliveryPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/delivery-party")
@RestController
@RequiredArgsConstructor
public class DeliveryPartyController {

    private final DeliveryPartyService deliveryPartyService;

    @PostMapping
    public void deliveryPartyAdd(
            @RequestBody @Valid DeliveryPartyRequest request
            ){

        deliveryPartyService.addDeliveryParty(request);
    }

    @GetMapping("/list")
    public DeliveryPartyListResponse deliveryPartyList(){

        return deliveryPartyService.findAllDeliveryParty();
    }

    @PostMapping("/join/{deliveryPartyId}")
    public void deliveryPartyJoin(
            @PathVariable Long deliveryPartyId
    ){

        deliveryPartyService.joinDeliveryParty(deliveryPartyId);
    }

    @DeleteMapping("/{deliveryPartyId}")
    public void deliveryPartyRemove(
            @PathVariable Long deliveryPartyId
    ){

        deliveryPartyService.deleteDeliveryParty(deliveryPartyId);
    }
}
