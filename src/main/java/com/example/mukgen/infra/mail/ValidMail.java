package com.example.mukgen.infra.mail;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(timeToLive = 60 * 1000)
public class ValidMail {

    @Id
    private String mail;
}
