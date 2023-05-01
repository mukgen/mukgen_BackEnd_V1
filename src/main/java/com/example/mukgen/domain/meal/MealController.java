package com.example.mukgen.domain.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealApi mealApi;

    @GetMapping("/meal/{riceType}/{year}/{month}/{day}")
    public MealApi.Rice getMeal(
            @PathVariable MealApi.RiceType riceType,
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day
    ){
        return mealApi.getRice("G10","7430310", riceType,year,month,day);
    }

}
