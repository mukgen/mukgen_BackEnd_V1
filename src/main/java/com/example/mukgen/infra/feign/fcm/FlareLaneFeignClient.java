package com.example.mukgen.infra.feign.fcm;

import com.example.mukgen.infra.feign.fcm.dto.FlareLaneRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "flarelaneclient",
        url = "https://api.flarelane.com/v1/projects/${FLARELANE_PROJECT_ID}/notifications"
)
public interface FlareLaneFeignClient {
    @PostMapping(consumes = "application/json", headers = "Authorization=Bearer ${FLARELANE_API_KEY}")
    public void sendMessage(@RequestBody FlareLaneRequest request);
}
