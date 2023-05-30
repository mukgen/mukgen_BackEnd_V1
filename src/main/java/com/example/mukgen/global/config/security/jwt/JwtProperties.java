package com.example.mukgen.global.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String header;

    private final String prefix;

    private final String secret;

    private final Long accessExpiration;

    private final Long refreshExpiration;

}
