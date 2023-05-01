package com.example.mukgen.domain.auth.controller;

import com.example.mukgen.domain.auth.controller.reponse.TokenResponse;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.request.UserModifyPasswordRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/general")
    public void signup(
            @RequestBody
            UserSignupRequest request
            ){
        authService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(
            @RequestBody
            UserLoginRequest request
            ){
        return authService.login(request);
    }

    @PostMapping("/change/password/{userId}")
    public void modifyUserPassword(
            @PathVariable String userId,
            @RequestBody UserModifyPasswordRequest request
            ){
        authService.UserModifyPassword(request,userId);
    }



}
