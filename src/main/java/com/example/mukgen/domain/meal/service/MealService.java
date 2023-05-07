package com.example.mukgen.domain.meal.service;


import com.example.mukgen.domain.meal.entity.Meal;
import com.example.mukgen.domain.meal.entity.MealType;
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

    private final ConcurrentHashMap<Integer, Meal> mealCache = new ConcurrentHashMap<>();

    @Transactional
    public MealResponse findMeal(MealRequest request){

        MealType mealType = request.getMealType();

        int year = request.getYear();

        int month = request.getMonth();

        int day = request.getDay();

        int addId = switch (mealType) {
            case BREAKFAST -> 1;
            case LUNCH -> 2;
            case DINNER -> 3;
        };

        int id = (year * 10000 + month * 100 + day) * 10 + addId;

        Meal meal = mealCache.get(id);

        if (meal == null) {

            if(!mealRepository.existsById(id)){
                meal = mealApi.getMeal(mealType, year, month, day);
                mealRepository.save(meal);
            }

            else {
                meal = mealRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("찾을 수 없습니다."));
            }
            mealCache.put(id, meal);
        }

        return MealResponse.builder()
                .item(meal.getItem())
                .build();
    }

    @Transactional
    public void downLoadAllMeal(){

        int day = 1;
        while(day<=30){
            try {
                Meal meal = mealApi.getMeal(MealType.LUNCH, 2023, 5, day);
                mealRepository.save(meal);
                meal = mealApi.getMeal(MealType.BREAKFAST, 2023, 5, day);
                mealRepository.save(meal);
                meal = mealApi.getMeal(MealType.DINNER, 2023, 5, day);
                mealRepository.save(meal);
            } catch (Exception e) {
                System.err.println("An error occurred while processing day " + day + ": " + e.getMessage());
            } finally {
                day++;
            }
        }


    }

}
