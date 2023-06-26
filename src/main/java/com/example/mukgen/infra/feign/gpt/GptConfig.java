package com.example.mukgen.infra.feign.gpt;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class GptConfig {

    private final GptProperty gptProperty;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate->{
            requestTemplate.header("Authorization", "Bearer " + gptProperty.getSecret());
        };
    }

}
