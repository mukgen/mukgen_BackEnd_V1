package com.example.mukgen.infra.mail;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public interface ValidMailRepository extends CrudRepository<ValidMail, String> {
}
