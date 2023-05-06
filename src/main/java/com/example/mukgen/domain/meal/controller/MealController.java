package com.example.mukgen.domain.meal.controller;

import com.example.mukgen.domain.meal.controller.dto.request.MealRequest;
import com.example.mukgen.domain.meal.controller.dto.response.MealResponse;
import com.example.mukgen.domain.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meal")
    public MealResponse getMeal(
            @RequestBody MealRequest request
            ){
        return mealService.findRice(request);
    }

    @PostMapping("/meal/download")
    public void downloadAllMeal(){
        mealService.downLoadAllRice();
    }

}
