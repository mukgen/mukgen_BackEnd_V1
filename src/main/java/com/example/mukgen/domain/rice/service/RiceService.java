package com.example.mukgen.domain.rice.service;


import com.example.mukgen.domain.rice.controller.dto.request.RiceRequest;
import com.example.mukgen.domain.rice.controller.dto.response.RiceResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceTodayResponse;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Transactional
@Service
public class RiceService {

    private final RiceApi riceApi;

    private final RiceRepository riceRepository;

    private final ConcurrentHashMap<Integer, Rice> mealCache = new ConcurrentHashMap<>();

    public RiceResponse findRice(RiceRequest request){

        RiceType riceType = request.getRiceType();

        int year = request.getYear();

        int month = request.getMonth();

        int day = request.getDay();

        int addId = switch (riceType) {
            case BREAKFAST -> 1;
            case LUNCH -> 2;
            case DINNER -> 3;
        };

        int id = (year * 10000 + month * 100 + day) * 10 + addId;

        Rice rice = mealCache.get(id);

        if (rice == null) {

            if(!riceRepository.existsById(id)){
                rice = riceApi.getRice(riceType, year, month, day);
                riceRepository.save(rice);
            }

            else {
                rice = riceRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("찾을 수 없습니다."));
            }
            mealCache.put(id, rice);
        }

        return RiceResponse.builder()
                .riceType(riceType.getTag())
                .riceId(id)
                .item(rice.getItem())
                .build();
    }

    public RiceTodayResponse findTodayRice(){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = curDate.getDayOfMonth();
        int month = curDate.getMonthValue();
        int year = curDate.getYear();

        List<RiceResponse> riceResponseList = new ArrayList<>();

        RiceType[] riceTypes = {RiceType.BREAKFAST, RiceType.LUNCH, RiceType.DINNER};

        for (RiceType riceType : riceTypes) {
            RiceRequest riceRequest = RiceRequest.builder()
                    .riceType(riceType)
                    .day(day)
                    .month(month)
                    .year(year)
                    .build();

            RiceResponse riceResponse = findRice(riceRequest);
            riceResponseList.add(riceResponse);
        }

        return RiceTodayResponse.builder()
                .responseList(riceResponseList)
                .build();
    }

    public void downLoadAllRice(int month){

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int day = YearMonth.now().atEndOfMonth().getDayOfMonth();

        int count=1;
        while(count<=day){
            try {
                RiceType[] riceTypes = {RiceType.LUNCH, RiceType.BREAKFAST, RiceType.DINNER};

                for (RiceType riceType : riceTypes) {
                    Rice rice = riceApi.getRice(riceType, curDate.getYear(), month, count);
                    riceRepository.save(rice);
                }
            } finally {
                count++;
            }
        }


    }

}
