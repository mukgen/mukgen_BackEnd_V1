package com.example.mukgen.domain.auth.service;


import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.response.LoginResponse;
import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.service.exception.PassWordCheckMismatchException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.entity.type.UserRole;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.UserAlreadyExistException;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
import com.example.mukgen.global.config.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import oauth2.InfoOAuth2;
import oauth2.dto.request.ExchangeTokenRequest;
import oauth2.dto.response.ResourceResponse;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final ObjectMapper objectMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(UserSignupRequest request){

        if(!request.getPassword().matches(request.getPasswordCheck())){
            throw PassWordCheckMismatchException.EXCEPTION;
        }

        validateDuplicateUser(request);

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .role(UserRole.STUDENT)
                .accountId(request.getAccountId())
                .name(request.getName())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

    }

    public LoginResponse infoAuth(String code){

        InfoOAuth2 oauth2 = new InfoOAuth2("mukgen", "92cb3fd4-798c-4242-aba2-4e55eade7714");

        oauth2.exchangeTokenWithoutPKCE(new ExchangeTokenRequest(
                code,
                "http://mukgen.info/info/oauth2"));

        try{
            ResourceResponse userResponse = oauth2.getUserResponse();
            String json = objectMapper.writeValueAsString(userResponse);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            User user = saveOrUpdate(dataObject);

            return LoginResponse.builder()
                    .tokenResponse(jwtTokenProvider.createToken(user.getAccountId()))
                    .message(user.getName() + "님 환영합니다!")
                    .build();
        }
        catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }

    }

    private User saveOrUpdate(JSONObject object){

        User user = userRepository.findByAccountId(object.getString("email"))
                .map(it -> it.update(object))
                .orElse(new User(object));
        return userRepository.save(user);
    }

    public LoginResponse login(UserLoginRequest request){
       User user = userRepository.findByAccountId(request.getAccountId())
               .orElseThrow(()-> UserNotFoundException.EXCEPTION);

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
