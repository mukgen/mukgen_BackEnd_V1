package com.example.mukgen.global.config.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class JwtResolver {

    private final JwtProperties jwtProperties;

    public String resolveToken(HttpServletRequest request){

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length()+1){
            return bearerToken.substring(7);
        }
        return null;
    }
}
