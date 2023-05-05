package com.example.mukgen.domain.meal.repository;


import com.example.mukgen.domain.meal.entity.Rice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Rice,Integer> {

    boolean existsById(int id);

}
