package com.example.mukgen.domain.meal.service;


import com.example.mukgen.domain.meal.entity.Rice;
import com.example.mukgen.domain.meal.entity.RiceType;
import com.example.mukgen.domain.meal.controller.dto.request.MealRequest;
import com.example.mukgen.domain.meal.controller.dto.response.MealResponse;
import com.example.mukgen.domain.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealService {

    private final MealApi mealApi;

    private final MealRepository mealRepository;

    private final ConcurrentHashMap<Integer, Rice> riceCache = new ConcurrentHashMap<>();

    @Transactional
    public MealResponse findRice(MealRequest request){

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

        Rice rice = riceCache.get(id);

        if (rice == null) {

            if(!mealRepository.existsById(id)){
                rice = mealApi.getRice(riceType, year, month, day);
                mealRepository.save(rice);
            }

            else {
                rice = mealRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("찾을 수 없습니다."));
            }
            riceCache.put(id, rice);
        }

        return MealResponse.builder()
                .item(rice.getItem())
                .build();
    }

    @Transactional
    public void downLoadAllRice(){

        int day = 1;
        while(day<=30){
            try {
                Rice rice = mealApi.getRice(RiceType.LUNCH, 2023, 5, day);
                mealRepository.save(rice);
                rice = mealApi.getRice(RiceType.BREAKFAST, 2023, 5, day);
                mealRepository.save(rice);
                rice = mealApi.getRice(RiceType.DINNER, 2023, 5, day);
                mealRepository.save(rice);
            } catch (Exception e) {
                System.err.println("An error occurred while processing day " + day + ": " + e.getMessage());
            } finally {
                day++;
            }
        }


    }

}
