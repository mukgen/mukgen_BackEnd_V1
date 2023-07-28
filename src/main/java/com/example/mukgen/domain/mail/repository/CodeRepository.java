package com.example.mukgen.domain.mail.repository;

import com.example.mukgen.domain.mail.entity.Code;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
@Configuration
public interface CodeRepository extends CrudRepository<Code, String> {

    boolean existsByCode(String code);
}
