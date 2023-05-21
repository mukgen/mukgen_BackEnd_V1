package com.example.mukgen.domain.deliveryparty.service;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.deliveryparty.repository.DeliveryPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryPartyScheduledService {

    private final DeliveryPartyRepository deliveryPartyRepository;

    @Scheduled(fixedDelay = 600000) // 10분마다 만난 시간이 지난 배달파티 삭제
    @Transactional
    public void deleteDeliveryParties(){

        LocalDateTime time = LocalDateTime.now();
        List<DeliveryParty> deliveryParties = deliveryPartyRepository.findAllByMeetTimeBefore(time);
        for(DeliveryParty deliveryParty : deliveryParties){
            deliveryPartyRepository.delete(deliveryParty);
        }
    }

}
