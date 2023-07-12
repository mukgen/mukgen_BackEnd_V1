package com.example.mukgen.domain.rice.service;

import com.example.mukgen.domain.rice.controller.dto.response.MukgenPickResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class MukgenPickScheduledService {

    private final RiceService riceService;

    @Scheduled(cron = "0 0 0 * * *")
    public void autoSetMukgenPick() throws JsonProcessingException {
        System.out.println("확인");
        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        MukgenPickResponse mukgenPick = riceService.findMukgenPick();
        if(mukgenPick.getMonth()<curDate.getMonthValue()){
            riceService.setMukgenPickAuto();
        }
        else if(mukgenPick.getMonth() == curDate.getMonthValue()){
            if(mukgenPick.getDay()<curDate.getDayOfMonth()){
                riceService.setMukgenPickAuto();
            }
        }
    }

}
