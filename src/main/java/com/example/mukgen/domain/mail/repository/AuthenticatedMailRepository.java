package com.example.mukgen.domain.mail.repository;

import com.example.mukgen.domain.mail.entity.AuthenticatedMail;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public interface AuthenticatedMailRepository extends CrudRepository<AuthenticatedMail, String> {
}
