package com.example.mukgen.domain.auth.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
@Getter
@Builder
@RedisHash(timeToLive = 60 * 60 * 120)
public class RefreshToken {

    @Id
    private String accountId;

    @Indexed
    private String token;
}
