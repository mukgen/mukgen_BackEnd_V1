package com.example.mukgen.domain.meal.repository;


import com.example.mukgen.domain.meal.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal,Integer> {

    boolean existsById(int id);

}
