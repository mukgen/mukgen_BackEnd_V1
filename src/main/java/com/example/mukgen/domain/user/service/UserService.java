package com.example.mukgen.domain.user.service;

import com.example.mukgen.domain.user.controller.response.UserProfileResponse;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse findProfile(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        return UserProfileResponse.builder()
                .name(user.getName())
                .userId(user.getUserId())
                .build();

    }
}
