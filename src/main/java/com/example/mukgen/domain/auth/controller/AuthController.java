package com.example.mukgen.domain.auth.controller;

import com.example.mukgen.domain.auth.controller.reponse.LoginResponse;
import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.controller.request.ChefSignupRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/general")
    public void signup(
            @RequestBody @Valid
            UserSignupRequest request
            ){
        authService.signup(request);
    }

    @PostMapping("/signup/chef")
    public void signup(
            @RequestBody @Valid
            ChefSignupRequest request
    ){
        authService.chefSignup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody
            UserLoginRequest request
            ){
        return authService.login(request);
    }



}
