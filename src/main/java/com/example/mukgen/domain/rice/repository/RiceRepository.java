package com.example.mukgen.domain.rice.repository;


import com.example.mukgen.domain.rice.entity.Rice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiceRepository extends JpaRepository<Rice,Integer> {

    boolean existsById(int id);

    List<Rice> findAllByMonth(int month);

}
