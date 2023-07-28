package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.response.LoginResponse;
import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.service.exception.InvalidMailException;
import com.example.mukgen.domain.auth.service.exception.PassWordCheckMismatchException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.PasswordMismatchException;
import com.example.mukgen.domain.user.service.exception.UserAlreadyExistException;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
import com.example.mukgen.global.config.security.jwt.JwtTokenProvider;
import com.example.mukgen.domain.mail.repository.AuthenticatedMailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final AuthenticatedMailRepository authenticatedMailRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(UserSignupRequest request){

        if (userRepository.existsByMail(request.getMail())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        if (!authenticatedMailRepository.existsById(request.getMail())) {
            throw InvalidMailException.EXCEPTION;
        }

        if(!request.getPassword().equals(request.getPasswordCheck())){
            throw PassWordCheckMismatchException.EXCEPTION;
        }

        validateDuplicateUser(request);

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .role(UserRole.STUDENT)
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(password)
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

        authenticatedMailRepository.deleteById(request.getMail());
    }

    public LoginResponse login(UserLoginRequest request){

       User user = userRepository.findByAccountId(request.getAccountId())
               .orElseThrow(()-> UserNotFoundException.EXCEPTION);

       if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
           throw PasswordMismatchException.EXCEPTION;
       }

       return LoginResponse.builder()
               .tokenResponse(jwtTokenProvider.createToken(user.getAccountId()))
               .message(user.getName() + "님 환영합니다!")
               .build();

    }

    public TokenResponse reIssue(String token){

        return jwtTokenProvider.reIssue(token);
    }


    private void validateDuplicateUser(UserSignupRequest request){

        if(userRepository.existsByAccountId(request.getAccountId())){
            throw UserAlreadyExistException.EXCEPTION;
        }
    }

}
