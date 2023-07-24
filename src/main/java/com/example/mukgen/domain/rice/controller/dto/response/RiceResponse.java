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
        List<String> items = new ArrayList<>();
        String addItem = "";
        for(int i=0;i<item.length();i++){
            if(item.charAt(i)!=',' && item.charAt(i)!='\"'){
                addItem+=item.charAt(i);
            }
            else if(item.charAt(i)==','){
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
