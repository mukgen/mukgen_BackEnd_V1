package com.example.mukgen.global.config.security;

import com.example.mukgen.global.config.FilterConfig;
import com.example.mukgen.global.config.security.auth.CustomUserDetailService;
import com.example.mukgen.global.config.security.jwt.JwtResolver;
import com.example.mukgen.global.config.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtResolver jwtResolver;

    private final ObjectMapper objectMapper;

    private final CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .exceptionHandling()

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/mukgen/chef/**").hasRole("CHEF")
                .antMatchers("/mukgen/meal").permitAll()
                .antMatchers("/mukgen/like").permitAll()
                .antMatchers("/mukgen/board-comment").permitAll()
                .antMatchers("/mukgen/review").permitAll()
                .antMatchers("/mukgen/review-comment").permitAll()
                .antMatchers("/mukgen/meal-suggestion").permitAll()
                .antMatchers("/mukgen/user").permitAll()
                .antMatchers("/mukgen/auth/**").permitAll()
                .antMatchers("/mukgen/board").permitAll()
                .antMatchers("/mukgen/delivery-party").permitAll()
                .antMatchers("/mukgen/actuator/**").permitAll()
                .antMatchers("/mukgen/mail/**").permitAll()
                .antMatchers("/mukgen/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider, jwtResolver, objectMapper, userDetailService))

                .and().build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://chef.mukgen.info"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // HTTP 메서드 허용
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
