package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.controller.request.UserModifyPasswordRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.PasswordMismatchException;
import com.example.mukgen.domain.user.service.exception.UserAlreadyExistException;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
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
    public void signup(UserSignupRequest request){

        String password = passwordEncoder.encode(request.getPassword());

        validateDuplicateUser(request);

        User user = User.builder()
                .role(UserRole.GENERAL)
                .accountId(request.getUserId())
                .password(password)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

    }

    @Transactional
    public TokenResponse login(UserLoginRequest request){
       User user = userRepository.findByAccountId(request.getUserId())
               .orElseThrow(()-> UserNotFoundException.EXCEPTION);

       if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
           throw PasswordMismatchException.EXCEPTION;
       }
       return TokenResponse.builder()
               .accessToken(jwtTokenProvider.createToken(user.getAccountId()))
               .build();
    }

    @Transactional
    public void UserModifyPassword(
            UserModifyPasswordRequest request,
            String userId
    ){

        if(request.getNewPassword().equals(request.getOldPassword())){
            throw new IllegalStateException("이전 비밀번호와 같습니다.");
        }

        String newPassword = passwordEncoder.encode(request.getNewPassword());

        User user = userRepository.findByAccountId(userId)
                .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 유저입니다."));

        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        user.changePassword(newPassword);
    }

    private void validateDuplicateUser(UserSignupRequest request){

        if(userRepository.existsByAccountId(request.getUserId())){
            throw UserAlreadyExistException.EXCEPTION;
        }
    }
}
