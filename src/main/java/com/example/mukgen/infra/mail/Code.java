package com.example.mukgen.infra.mail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(timeToLive = 60 * 5)
public class Code {

    @Id
    private String mail;

    private String code;

    public Code(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }
}
