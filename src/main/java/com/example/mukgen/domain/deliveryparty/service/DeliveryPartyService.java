package com.example.mukgen.domain.deliveryparty.service;

import com.example.mukgen.domain.deliveryparty.controller.dto.request.DeliveryPartyRequest;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyListResponse;
import com.example.mukgen.domain.deliveryparty.controller.dto.response.DeliveryPartyResponse;
import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.deliveryparty.repository.DeliveryPartyRepository;
import com.example.mukgen.domain.deliveryparty.service.exception.*;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class DeliveryPartyService {

    private final UserRepository userRepository;

    private final DeliveryPartyRepository deliveryPartyRepository;

    private final UserFacade userFacade;

    public void addDeliveryParty(
        DeliveryPartyRequest request
    ){

        User user = userFacade.currentUser();

        if(deliveryPartyRepository.existsByWriterAccountId(user.getAccountId())){
            throw DeliveryPartyAlreadyExistsException.EXCEPTION;
        }

        if(request.getMeetTime().isBefore(LocalDateTime.now())){
            throw DeliveryPartyMeetTimeException.EXCEPTION;
        }

        DeliveryParty deliveryParty = DeliveryParty.builder()
                .user(user)
                .place(request.getPlace())
                .menu(request.getMenu())
                .participantNumber(request.getParticipantNumber())
                .meetTime(request.getMeetTime())
                .build();

        deliveryParty.joinDeliveryParty(userFacade.currentUser());

        deliveryPartyRepository.save(deliveryParty);
    }

    @Transactional(readOnly = true)
    public DeliveryPartyListResponse findAllDeliveryParty(){

        return DeliveryPartyListResponse.builder()
                .deliveryPartyResponseList(
                        deliveryPartyRepository
                                .findAllByMeetTimeAfter(LocalDateTime.now())
                                .stream()
                                .map(it -> DeliveryPartyResponse.of(
                                        it,
                                        userRepository.findByAccountId(it.getWriterAccountId())
                                                .orElseThrow(()-> UserNotFoundException.EXCEPTION)))
                                .toList())
                .build();
    }

    public void joinDeliveryParty(
            Long deliveryPartyId
    ){

        DeliveryParty deliveryParty = deliveryPartyRepository.findById(deliveryPartyId)
                .orElseThrow(()-> DeliveryPartyNotFoundException.EXCEPTION);

        if(deliveryParty.getUserList().size()>=deliveryParty.getParticipantNumber()){
            throw DeliveryPartyFullException.EXCEPTION;
        }

        User user = userFacade.currentUser();

        if(deliveryPartyRepository.existsByWriterAccountId(user.getAccountId())){

            throw DeliveryPartyInProgressException.EXCEPTION;
        }

        deliveryParty.joinDeliveryParty(user);

    }

    public void leaveDeliveryParty(
            Long deliveryPartyId
    ){

        User curUser = userFacade.currentUser();

        DeliveryParty deliveryParty = deliveryPartyRepository.findById(deliveryPartyId)
                .orElseThrow(()-> DeliveryPartyNotFoundException.EXCEPTION);

        if(!deliveryParty.getUserList().contains(curUser)){
            throw DeliveryPartyNotJoinException.EXCEPTION;
        }
        if(deliveryParty.getWriterAccountId().equals(curUser.getAccountId())){
            throw DeliveryPartyCantLeaveException.EXCEPTION;
        }

        deliveryParty.leaveDeliveryParty(curUser);
    }

    public void deleteDeliveryParty(
            Long deliveryPartyId
    ){

        User curUser = userFacade.currentUser();

        DeliveryParty deliveryParty = deliveryPartyRepository.findById(deliveryPartyId)
                .orElseThrow(()-> DeliveryPartyNotFoundException.EXCEPTION);

        if(!curUser.getAccountId().equals(deliveryParty.getWriterAccountId())){

            throw DeliveryPartyWriterMismatchException.EXCEPTION;
        }

        List<User> users = new ArrayList<>(deliveryParty.getUserList());

        for (User user : users) {
            user.setDeliveryParty(null);
        }

        deliveryPartyRepository.deleteById(deliveryPartyId);
    }

    public void autoDeleteDeliveryParty(
            Long deliveryPartyId
    ){

        DeliveryParty deliveryParty = deliveryPartyRepository.findById(deliveryPartyId)
                .orElseThrow(() -> DeliveryPartyNotFoundException.EXCEPTION);

        List<User> users = new ArrayList<>(deliveryParty.getUserList());

        for (User user : users) {
            user.setDeliveryParty(null);
        }

        deliveryPartyRepository.deleteById(deliveryPartyId);
    }
}
