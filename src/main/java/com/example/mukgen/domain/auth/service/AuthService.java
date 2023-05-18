package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.reponse.LoginResponse;
import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.controller.request.ChefSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.service.exception.CodeMismatchException;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void chefSignup(
            ChefSignupRequest request
    ){

        if (!request.getCode().equals("imChef")) {
            throw CodeMismatchException.EXCEPTION;
        }

        User user = User.builder()
                .role(UserRole.CHEF)
                .accountId(request.getAccountId())
                .password(request.getPassword())
                .name("영양사 선생님")
                .phoneNumber("영양사 선생님은 번호를 입력하지 않습니다.")
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void signup(UserSignupRequest request){

        String password = passwordEncoder.encode(request.getPassword());

        validateDuplicateUser(request);

        User user = User.builder()
                .role(UserRole.GENERAL)
                .accountId(request.getAccountId())
                .password(password)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

    }

    @Transactional
    public LoginResponse login(UserLoginRequest request){
       User user = userRepository.findByAccountId(request.getAccountId())
               .orElseThrow(()-> UserNotFoundException.EXCEPTION);

       if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
           throw PasswordMismatchException.EXCEPTION;
       }
       return LoginResponse.builder()
               .tokenResponse(TokenResponse.builder()
                       .accessToken(jwtTokenProvider.createToken(user.getAccountId()))
                       .build())
               .message(user.getName() + "님 환영합니다!")
               .build();

    }

    private void validateDuplicateUser(UserSignupRequest request){

        if(userRepository.existsByAccountId(request.getAccountId())){
            throw UserAlreadyExistException.EXCEPTION;
        }
    }
}
