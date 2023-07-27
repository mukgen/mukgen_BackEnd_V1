package com.example.mukgen.infra.mail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ValidMailRepository extends CrudRepository<String, String> {
}
