package com.example.mukgen.domain.auth.controller;

import com.example.mukgen.domain.auth.controller.request.*;
import com.example.mukgen.domain.auth.controller.response.LoginResponse;
import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/re-issue")
    public TokenResponse reIssue(
            @RequestBody ReIssueRequest request
            ){
        return authService.reIssue(request.getRefreshToken());
    }

    @PutMapping("/modify-password/{userId}")
    public void passwordModify(
            @PathVariable Long userId,
            @RequestBody UserModifyPasswordRequest request
    ) {
        authService.modifyPassword(userId, request);
    }
}
