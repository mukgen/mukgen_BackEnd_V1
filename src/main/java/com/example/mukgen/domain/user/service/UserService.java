package com.example.mukgen.domain.user.service;

import com.example.mukgen.domain.user.controller.response.UserProfileResponse;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse findProfile(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow( () -> UserNotFoundException.EXCEPTION);

        return UserProfileResponse.builder()
                .name(user.getName())
                .userId(user.getAccountId())
                .build();

    }
}
