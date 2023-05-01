package com.example.mukgen.domain.user.repository;

import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByAccountId(String userId);

    boolean existsByAccountId(String userId);

}
