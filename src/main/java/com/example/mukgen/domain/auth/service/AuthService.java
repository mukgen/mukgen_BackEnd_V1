package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.board.controller.dto.request.UserModifyPasswordRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.PasswordMismatchException;
import com.example.mukgen.domain.user.service.exception.SamePasswordException;
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
        User user = userRepository.findByAccountId(userId)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if(request.getNewPassword().equals(request.getOldPassword())){
            throw SamePasswordException.EXCEPTION;
        }

        String newPassword = passwordEncoder.encode(request.getNewPassword());



        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword())){
            throw PasswordMismatchException.EXCEPTION;
        }
        user.changePassword(newPassword);
    }

    private void validateDuplicateUser(UserSignupRequest request){

        if(userRepository.existsByAccountId(request.getUserId())){
            throw UserAlreadyExistException.EXCEPTION;
        }
    }
}