package com.example.mukgen.infra.feign.client;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NeisUtil {

    private final RiceRepository riceRepository;

    private final NeisFeignClient neisFeignClient;

    public List<Rice> findRice(String type, String code, String schoolCode, String day){

        String jsonStr = neisFeignClient.getRice(type, code, schoolCode, day);

        List<Rice> riceList = new ArrayList<>();

        JSONObject obj = new JSONObject(jsonStr);
        if(obj.has("mealServiceDietInfo")){
            JSONArray mealServiceDietInfoArray = obj.getJSONArray("mealServiceDietInfo");

            // Iterate through each mealServiceDietInfo element
            for (int i = 0; i < mealServiceDietInfoArray.length(); i++) {
                JSONObject mealServiceDietInfo = mealServiceDietInfoArray.getJSONObject(i);

                if(mealServiceDietInfo.has("row")){
                    JSONArray rowArray = mealServiceDietInfo.getJSONArray("row");

                    // Iterate through each row element
                    for (int j = 0; j < rowArray.length(); j++) {
                        JSONObject row = rowArray.getJSONObject(j);
                        String mealType = row.getString("MMEAL_SC_NM");
                        String mealName = row.getString("DDISH_NM");
                        String mealYMD = row.getString("MLSV_YMD");

                        switch(mealType){
                            case "조식":
                                riceList.add(riceRepository.save(Rice.builder()
                                        .id(Integer.parseInt(mealYMD) * 10 + 1)
                                        .item(cleanMealName(mealName))
                                        .riceType(RiceType.BREAKFAST)
                                        .build()));
                                break;
                            case "중식":
                                riceList.add(riceRepository.save(Rice.builder()
                                        .id(Integer.parseInt(mealYMD)*10+2)
                                        .item(cleanMealName(mealName))
                                        .riceType(RiceType.LUNCH)
                                        .build()));
                                break;
                            case "석식":
                                riceList.add(riceRepository.save(Rice.builder()
                                        .id(Integer.parseInt(mealYMD)*10+3)
                                        .item(cleanMealName(mealName))
                                        .riceType(RiceType.DINNER)
                                        .build()));
                                break;
                        }
                    }
                }
            }
        }

        return riceList;
    }

    public String cleanMealName(String mealName) {
        return mealName.replaceAll("\\(([^(^)]+)\\)", "").replaceAll("([.*\\-]+)", "").replace("<br/>", ",").replace(" ","");
    }
}
