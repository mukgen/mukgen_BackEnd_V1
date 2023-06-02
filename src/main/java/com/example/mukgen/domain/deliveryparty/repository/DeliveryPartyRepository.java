package com.example.mukgen.domain.deliveryparty.repository;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryPartyRepository extends JpaRepository<DeliveryParty, Long> {

    boolean existsByWriterAccountId(String writerAccountId);

    List<DeliveryParty> findAllByMeetTimeAfter(LocalDateTime time);

    List<DeliveryParty> findAllByMeetTimeBefore(LocalDateTime time);

}
