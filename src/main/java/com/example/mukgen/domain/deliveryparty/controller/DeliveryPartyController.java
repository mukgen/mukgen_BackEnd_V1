package com.example.mukgen.domain.deliveryparty.controller;

import com.example.mukgen.domain.deliveryparty.controller.dto.request.DeliveryPartyRequest;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyListResponse;
import com.example.mukgen.domain.deliveryparty.service.DeliveryPartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/mukgen/delivery-party")
@RestController
public class DeliveryPartyController {

    private final DeliveryPartyService deliveryPartyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    @PostMapping("/leave/{deliveryPartyId}")
    public void deliveryPartyLeave(
            @PathVariable Long deliveryPartyId
    ){
        deliveryPartyService.leaveDeliveryParty(deliveryPartyId);
    }

    @DeleteMapping("/{deliveryPartyId}")
    public void deliveryPartyRemove(
            @PathVariable Long deliveryPartyId
    ){

        deliveryPartyService.deleteDeliveryParty(deliveryPartyId);
    }
}
