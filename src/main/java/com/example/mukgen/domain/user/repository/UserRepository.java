package com.example.mukgen.domain.user.repository;

import com.example.mukgen.domain.deliveryparty.entity.DeliveryParty;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByAccountId(String userId);

    boolean existsByAccountId(String userId);

    Optional<User> findByDeliveryParty(DeliveryParty deliveryParty);

}
