package com.example.mukgen.domain.auth.controller;

import com.example.mukgen.domain.auth.controller.request.ReIssueRequest;
import com.example.mukgen.domain.auth.controller.request.UserLoginRequest;
import com.example.mukgen.domain.auth.controller.request.UserSignupRequest;
import com.example.mukgen.domain.auth.controller.response.LoginResponse;
import com.example.mukgen.domain.auth.controller.response.TokenResponse;
import com.example.mukgen.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody
            UserLoginRequest request
            ){
        return authService.login(request);
    }

    @PostMapping("/login/chef")
    public TokenResponse loginChef(@RequestBody UserLoginRequest request){
        return authService.loginChef(request);
    }

    @PostMapping("/signup/general")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody @Valid UserSignupRequest request){
        authService.signup(request);
    }

    @PostMapping("/re-issue")
    public TokenResponse reIssue(
            @RequestBody ReIssueRequest request
            ){
        return authService.reIssue(request.getRefreshToken());
    }

    @GetMapping("/duplicate")
    public boolean accountIdDuplicateCheck(@RequestParam("accountId") String accountId) {
        return authService.checkAccountIdDuplicate(accountId);
    }
}
