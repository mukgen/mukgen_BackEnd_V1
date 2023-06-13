package com.example.mukgen.domain.auth.controller;

import com.example.mukgen.domain.auth.controller.request.CodeRequest;
import com.example.mukgen.domain.auth.controller.request.ReIssueRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.response.LoginResponse;
import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/info")
    public LoginResponse infoSignUp(
            @RequestBody CodeRequest request
    ){
        return authService.infoAuth(request.getCode());
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

}
