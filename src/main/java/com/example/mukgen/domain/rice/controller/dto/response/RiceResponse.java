package com.example.mukgen.domain.rice.controller.dto.response;

import com.example.mukgen.domain.rice.entity.Rice;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class RiceResponse {

    private String riceType;

    private List<String> items;

    private int riceId;

    public static RiceResponse of(Rice rice){
        String item = rice.getItem();
        if(item.equals("등록된 급식이 없습니다.")){
            List<String> items = new ArrayList<>();
            items.add(item);
            return RiceResponse.builder()
                    .riceId(rice.getId())
                    .items(items)
                    .riceType(rice.getRiceType().getTag())
                    .build();
        }
        List<String> items = new ArrayList<>();
        String addItem = "";
        for(int i=0;i<item.length();i++){
            if(item.charAt(i)!=',' && item.charAt(i)!='\"'){
                addItem+=item.charAt(i);
            }
            else if(item.charAt(i)==',' || i == item.length()-1){
                items.add(addItem);
                addItem="";
            }
        }
        return RiceResponse.builder()
                .riceId(rice.getId())
                .items(items)
                .riceType(rice.getRiceType().getTag())
                .build();
    }
}
