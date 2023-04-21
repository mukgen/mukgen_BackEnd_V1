package com.example.mukgen.domain.user.controller;

import com.example.mukgen.domain.user.controller.response.UserProfileResponse;
import com.example.mukgen.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{userId}")
    public UserProfileResponse getProfile(
            @PathVariable Long userId
    ){
        return userService.findProfile(userId);
    }
    

}
