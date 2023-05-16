package com.example.mukgen.domain.deliveryparty.service;

import com.example.mukgen.domain.deliveryparty.controller.dto.request.DeliveryPartyRequest;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyListResponse;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyResponse;
import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.deliveryparty.repository.DeliveryPartyRepository;
import com.example.mukgen.domain.deliveryparty.service.exception.DeliveryPartyNotFoundException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryPartyService {

    private final DeliveryPartyRepository deliveryPartyRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addDeliveryParty(
        DeliveryPartyRequest request
    ){

        DeliveryParty deliveryParty = DeliveryParty.builder()
                .place(request.getPlace())
                .menu(request.getMenu())
                .participantNumber(request.getParticipantNumber())
                .meetTime(request.getMeetTime())
                .build();

        deliveryParty.joinDeliveryParty(userFacade.currentUser());

        deliveryPartyRepository.save(deliveryParty);
    }

    public DeliveryPartyListResponse findAllDeliveryParty(){

        return DeliveryPartyListResponse.builder()
                .deliveryPartyResponseList(
                        deliveryPartyRepository
                                .findAll()
                                .stream()
                                .map(DeliveryPartyResponse::of)
                                .toList())
                .build();
    }

    @Transactional
    public void joinDeliveryParty(
            Long deliveryPartyId
    ){

        User user = userFacade.currentUser();

        DeliveryParty deliveryParty = deliveryPartyRepository.findById(deliveryPartyId)
                .orElseThrow(()-> DeliveryPartyNotFoundException.EXCEPTION);

        deliveryParty.joinDeliveryParty(user);

    }
}
