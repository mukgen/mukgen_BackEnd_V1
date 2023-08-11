package com.example.mukgen.infra.fcm;

import com.example.mukgen.infra.feign.fcm.FlareLaneFeignClient;
import com.example.mukgen.infra.feign.fcm.dto.FlareLaneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class FlareLaneUtil {

    private final FlareLaneProperty flareLaneProperty;

    private final FlareLaneFeignClient flareLaneFeignClient;

    public void sendMessage(String title, String body){
        FlareLaneRequest flareLaneRequest = new FlareLaneRequest(
                "segment",
                new ArrayList<>(Arrays.asList(flareLaneProperty.getSegmentsId())),
                title,
                body
        );
        flareLaneFeignClient.sendMessage(flareLaneRequest);
    }

}
