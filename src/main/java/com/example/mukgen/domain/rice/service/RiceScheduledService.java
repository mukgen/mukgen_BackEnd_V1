package com.example.mukgen.domain.rice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class RiceScheduledService {

    private final RiceService riceService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void autoDownLoadRice(){
        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        riceService.downLoadAllRice(curDate.getMonthValue());
    }

}
