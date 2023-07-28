package com.example.mukgen.domain.mail.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;

@Getter
@Builder
@RedisHash(timeToLive = 60 * 60)
public class ValidMail {

    @Id
    @Column(name = "mail", nullable = false)
    private String mail;

    public ValidMail(String mail) {
        this.mail = mail;
    }
}
