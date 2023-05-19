package com.example.mukgen.domain.rice.service;


import com.example.mukgen.domain.rice.controller.dto.response.RiceTodayResponse;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import com.example.mukgen.domain.rice.controller.dto.request.RiceRequest;
import com.example.mukgen.domain.rice.controller.dto.response.RiceResponse;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RiceService {

    private final RiceApi riceApi;

    private final RiceRepository riceRepository;

    private final ConcurrentHashMap<Integer, Rice> mealCache = new ConcurrentHashMap<>();

    @Transactional
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
                .riceId(id)
                .item(rice.getItem())
                .build();
    }

    @Transactional
    public RiceTodayResponse findTodayRice(){

        LocalDate curDate = LocalDate.now();

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

    @Transactional
    public void downLoadAllRice(){

        int day = 1;
        while(day<=30){
            try {
                RiceType[] riceTypes = {RiceType.LUNCH, RiceType.BREAKFAST, RiceType.DINNER};

                for (RiceType riceType : riceTypes) {
                    Rice rice = riceApi.getRice(riceType, 2023, 5, day);
                    riceRepository.save(rice);
                }
            } finally {
                day++;
            }
        }


    }

}
