package com.example.mukgen.domain.mail.repository;

import com.example.mukgen.domain.mail.entity.ValidMail;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public interface ValidMailRepository extends CrudRepository<ValidMail, String> {
}
