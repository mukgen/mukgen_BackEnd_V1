package com.example.mukgen.domain.rice.repository;

import com.example.mukgen.domain.rice.entity.MukgenPick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MukgenPickRepository extends JpaRepository<MukgenPick, Long> {

    Optional<MukgenPick> findFirstByOrderByCreatedAtDesc();

}
