package com.example.mukgen.global.config.security.jwt;


import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.entity.RefreshToken;
import com.example.mukgen.domain.auth.repository.RefreshTokenRepository;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.global.config.security.auth.CustomUserDetailService;
import com.example.mukgen.global.exception.ExpiredTokenException;
import com.example.mukgen.global.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final String secretKey = "mukgenproject123!@#mukgenproject123!@#mukgenproject123!@#";

    private final String prefix = "Bearer ";

    private final Long accessExpiredExp = 60 * 30 * 1000L;

    private final Long refreshExpiredExp = 60 * 60 * 120 * 1000L;

    private final RefreshTokenRepository refreshTokenRepository;

    private final CustomUserDetailService userDetailsService;

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

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
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
                .setExpiration(new Date(now.getTime() + accessExpiredExp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String createRefreshToken(){

        Date now = new Date();

        String rfToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExpiredExp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return rfToken;
    }


    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public String getUserPk(String token){
        try{
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (ExpiredJwtException e){
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e){
            throw InvalidTokenException.EXCEPTION;
        }

    }

    public String resolveToken(HttpServletRequest request){

        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix)
                && bearerToken.length() > prefix.length()+1){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateTokenExp(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
