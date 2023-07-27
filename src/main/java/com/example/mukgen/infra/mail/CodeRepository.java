package com.example.mukgen.infra.mail;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableRedisRepositories
@Repository
public interface CodeRepository extends CrudRepository<Code, String> {

    boolean existsByCode(String code);
}
