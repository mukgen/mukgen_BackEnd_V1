package com.example.mukgen.domain.lunchboard.repository;

import com.example.mukgen.domain.lunchboard.entity.LunchBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchBoardRepository extends JpaRepository<LunchBoard, Long> {
}
