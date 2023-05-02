package com.example.mukgen.domain.meal.controller;

import com.example.mukgen.domain.meal.service.MealApi;
import com.example.mukgen.domain.meal.entity.Rice;
import com.example.mukgen.domain.meal.entity.RiceType;
import com.example.mukgen.domain.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meal/{riceType}/{year}/{month}/{day}")
    public Rice getMeal(
            @PathVariable RiceType riceType,
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day
    ){
        return mealService.findRice(riceType,year,month,day);
    }

}
