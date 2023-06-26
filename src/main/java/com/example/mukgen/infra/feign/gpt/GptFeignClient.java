package com.example.mukgen.infra.feign.gpt;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "gptclient", url = "https://api.openai.com/v1/chat/completions")
public interface GptFeignClient {

    @PostMapping(
            headers = "Authorization=Bearer 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",
            produces = "application/json"
    )
    public GptResponse getResponse(@RequestBody GptFeignRequest request);
}
