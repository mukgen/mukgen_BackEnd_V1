package com.example.mukgen.global.config.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(value = "jwt")
public class JwtProperties {

    private final String header;

    private final String prefix;

    private final String secret;

    private final Long accessExpiration;

    private final Long refreshExpiration;

}
