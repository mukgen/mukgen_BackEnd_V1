package com.example.mukgen.infra.feign.gpt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GptProperty {

    @Value("${gpt.secret}")
    private String secret;
}
