package com.example.mukgen.domain.deliveryparty.service;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.deliveryparty.repository.DeliveryPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryPartyScheduledService {

    private final DeliveryPartyService deliveryPartyService;

    private final DeliveryPartyRepository deliveryPartyRepository;

    @Scheduled(fixedDelay = 600000) // 10분마다 만난 시간이 지난 배달파티 삭제
    @Transactional
    public void deleteDeliveryParties(){

        List<DeliveryParty> deliveryPartyList = deliveryPartyRepository
                .findAllByMeetTimeBefore(LocalDateTime.now());

        List<Long> idList = deliveryPartyList.stream()
                .map(DeliveryParty::getId)
                .toList();

        for(Long id : idList){
            deliveryPartyService.autoDeleteDeliveryParty(id);
        }
    }

}
