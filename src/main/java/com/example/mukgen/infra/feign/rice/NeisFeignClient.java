package com.example.mukgen.infra.feign.rice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client", url = "https://open.neis.go.kr/hub", configuration = Config.class)
public interface NeisFeignClient {

    @GetMapping("/mealServiceDietInfo")
    String getRice(
            @RequestParam("Type") String type,
            @RequestParam("ATPT_OFCDC_SC_CODE") String code,
            @RequestParam("SD_SCHUL_CODE") String schoolCode,
            @RequestParam("MLSV_YMD") String day
    );

}
