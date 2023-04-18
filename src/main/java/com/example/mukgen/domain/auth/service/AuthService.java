package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserSignupRequest request){

        String password = passwordEncoder.encode(request.getPassword());

        validateDuplicateMember(request);

        User user = User.builder()
                .userId(request.getUserId())
                .password(password)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

    }

    @Transactional
    public TokenResponse login(UserLoginRequest request){
       User user = userRepository.findByUserId(request.getUserId())
               .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 유저입니다."));

       if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
           throw new IllegalStateException("잘못된 비밀번호 입니다.");
       }
       return TokenResponse.builder()
               .accessToken(jwtTokenProvider.createToken(user.getUserId()))
               .build();
    }

    private void validateDuplicateMember(UserSignupRequest request){

        if(userRepository.existsByUserId(request.getUserId())){
            throw new IllegalStateException("이미 존재하는 유저입니다.");
        }
    }
}
