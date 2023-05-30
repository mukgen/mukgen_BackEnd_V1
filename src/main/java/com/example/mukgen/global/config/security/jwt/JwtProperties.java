package com.example.mukgen.global.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
public class JwtProperties {

    @Value("${spring.jwt.header}")
    private final String header;
    @Value("${spring.jwt.prefix}")
    private final String prefix;

    @Value("${spring.jwt.secret}")
    private final String secret;
    @Value("${spring.jwt.accessExpiration}")
    private final Long accessExpiration;

    @Value("${spring.jwt.refreshExpiration}")
    private final Long refreshExpiration;

}
