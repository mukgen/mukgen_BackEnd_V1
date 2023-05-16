package com.example.mukgen.domain.deliveryparty.repository;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartyRepository extends JpaRepository<DeliveryParty, Long> {

    boolean existsByWriterAccountId(String writerAccountId);
}
