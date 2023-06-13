package com.example.mukgen.global.config.properties;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class InfoOAuthProperties {

    @Value("${info.oauth.clientId}")
    private String clientId;

    @Value("${info.oauth.clientSecret}")
    private String secretId;

    @Value("${info.oauth.redirectUri}")
    private String redirectUri;
}
