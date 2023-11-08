package com.example.mukgen.global.config.security.jwt;


import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.entity.RefreshToken;
import com.example.mukgen.domain.auth.repository.RefreshTokenRepository;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.global.exception.ExpiredTokenException;
import com.example.mukgen.global.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public TokenResponse createToken(String accountId) {

        String accessToken = createAccessToken(accountId);
        String refreshToken = createRefreshToken();

        refreshTokenRepository.save(
            RefreshToken.builder()
                .accountId(accountId)
                .token(refreshToken)
                .build()
        );

        Date now = new Date();

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessTokenExp(Date(now.getTime() + jwtProperties.getAccessExpiredExp))
                .refreshToken(refreshToken)
                .refreshTokenExp(Date(now.getTime() + jwtProperties.getRefreshExpiredExp))
                .build();
    }

    public TokenResponse reIssue(String rfToken) {

        RefreshToken token = refreshTokenRepository.findByToken(rfToken)
                .orElseThrow(()-> InvalidTokenException.EXCEPTION);

        String accountId = userRepository.findByAccountId(token.getAccountId())
                .orElseThrow(() -> InvalidTokenException.EXCEPTION).getAccountId();

        refreshTokenRepository.delete(token);

        return createToken(accountId);
    }


    //JWT 토큰 생성
    private String createAccessToken(String accountId) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(accountId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiredExp()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    private String createRefreshToken(){

        Date now = new Date();

        String rfToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiredExp()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        return rfToken;
    }

    //토큰에서 회원 정보 추출
    private Claims getBody(String token){
        try{
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token).getBody();
        } catch (JwtException e){
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public String getSubjectWithExpiredCheck(String token){

        Claims body = getBody(token);

        if(body.getExpiration().before(new Date())) {
           throw ExpiredTokenException.EXCEPTION;
        } else {
            return body.getSubject();
        }
    }
}
