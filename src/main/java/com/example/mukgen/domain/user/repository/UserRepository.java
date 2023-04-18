package com.example.mukgen.domain.user.repository;

import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
