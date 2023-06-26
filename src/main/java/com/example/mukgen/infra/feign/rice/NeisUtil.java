package com.example.mukgen.infra.feign.rice;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.entity.RiceType;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NeisUtil {

    private final RiceRepository riceRepository;

    private final NeisFeignClient neisFeignClient;

    public Rice findRice(String type, String code, String schoolCode, String day, RiceType riceType){

        final JSONObject data = new JSONObject(neisFeignClient.getRice(type, code, schoolCode, day));

        int addId = switch (riceType) {
            case BREAKFAST -> 1;
            case LUNCH -> 2;
            case DINNER -> 3;
        };
        int id = Integer.parseInt(day)*10 + addId;
        if (data.has("mealServiceDietInfo")) {
            final JSONArray array = data.getJSONArray("mealServiceDietInfo").getJSONObject(1).getJSONArray("row");
            int length = array.length();
            for (int i = 0; i < length; i++) {
                final JSONObject json = array.getJSONObject(i);
                if (json.getString("MMEAL_SC_NM").equals(riceType.getRealTag())) {
                    return Rice.builder()
                            .riceType(riceType)
                            .item(cleanMealName(json.getString("DDISH_NM")))
                            .id(Integer.parseInt(day)*10 + addId)
                            .month(Integer.parseInt(day.substring(4, 6)))
                            .build();
                }
            }
        }
        return new Rice(id);
    }

    public String cleanMealName(String mealName) {
        return mealName.replaceAll("\\(([^(^)]+)\\)", "").replaceAll("([.*\\-]+)", "").replace("<br/>", ",").replace(" ","");
    }

}
