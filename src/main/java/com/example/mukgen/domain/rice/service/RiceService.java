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
                .item(rice.getItem())
                .build();
    }

    @Transactional
    public RiceTodayResponse findTodayRice(int year, int month, int day){
        List<RiceResponse> riceResponseList = new ArrayList<>();

        riceResponseList.add(findRice(
                RiceRequest.builder()
                        .riceType(RiceType.BREAKFAST)
                        .day(day)
                        .month(month)
                        .year(year)
                        .build()
        ));
        riceResponseList.add(findRice(
                RiceRequest.builder()
                        .riceType(RiceType.LUNCH)
                        .day(day)
                        .month(month)
                        .year(year)
                        .build()
        ));
        riceResponseList.add(findRice(
                RiceRequest.builder()
                        .riceType(RiceType.DINNER)
                        .day(day)
                        .month(month)
                        .year(year)
                        .build()
        ));

        return RiceTodayResponse.builder()
                .responseList(riceResponseList)
                .build();
    }

    @Transactional
    public void downLoadAllRice(){

        int day = 1;
        while(day<=30){
            try {
                Rice rice = riceApi.getRice(RiceType.LUNCH, 2023, 5, day);
                riceRepository.save(rice);
                rice = riceApi.getRice(RiceType.BREAKFAST, 2023, 5, day);
                riceRepository.save(rice);
                rice = riceApi.getRice(RiceType.DINNER, 2023, 5, day);
                riceRepository.save(rice);
            } catch (Exception e) {
                System.err.println("An error occurred while processing day " + day + ": " + e.getMessage());
            } finally {
                day++;
            }
        }


    }

}
