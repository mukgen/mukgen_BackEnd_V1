package com.example.mukgen.global.config.redis.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class RedisProperty {

    @Value("spring.redis.host")
    private String host;

    @Value("spring.redis.port")
    private int port;
}
