package com.example.mukgen.domain.rice.controller;

import com.example.mukgen.domain.rice.controller.dto.request.RiceRequest;
import com.example.mukgen.domain.rice.controller.dto.response.MukgenPickResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceMonthListResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceResponse;
import com.example.mukgen.domain.rice.controller.dto.response.RiceTodayResponse;
import com.example.mukgen.domain.rice.entity.MukgenPick;
import com.example.mukgen.domain.rice.service.RiceService;
import com.example.mukgen.infra.feign.rice.NeisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RestController
public class RiceController {

    private final RiceService riceService;

    private final NeisUtil neisUtil;

    @GetMapping("/meal")
    public RiceResponse mealDetails(
            @RequestBody RiceRequest request
            ){
        return riceService.findRice(request);
    }

    @GetMapping("/meal/today")
    public RiceTodayResponse mealTodayList(){
        return riceService.findTodayRice();
    }

    @PostMapping("/meal/download")
    @ResponseStatus(HttpStatus.CREATED)
    public void mealDownload(){
        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        riceService.downLoadAllRice(curDate.getMonthValue());
    }

    @PostMapping("/mukgen-pick")
    @ResponseStatus(HttpStatus.CREATED)
    public MukgenPick mukgenPickAdd() throws JsonProcessingException {
        return riceService.setMukgenPick();
    }

    @GetMapping("/mukgen-pick")
    public MukgenPickResponse mukgenPickDetails(){
        return riceService.findMukgenPick();
    }

    @GetMapping("/meal/month/{month}")
    public RiceMonthListResponse mealMonthList(@PathVariable int month){
        return riceService.findMonthRices(month);
    }

}
