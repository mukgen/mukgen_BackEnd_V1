package com.example.mukgen.domain.mail.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(timeToLive = 60 * 5)
public class Code {

    @Id
    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "code", length = 6, nullable = false)
    private String code;

    public Code(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }
}
