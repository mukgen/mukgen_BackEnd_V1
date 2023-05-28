package com.example.mukgen.global.config.security.auth;

import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {

        return userRepository.findByAccountId(accountId)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("찾을 수 없는 유저입니다."));

    }
}
