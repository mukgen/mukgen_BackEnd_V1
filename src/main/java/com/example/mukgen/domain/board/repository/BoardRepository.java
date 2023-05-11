package com.example.mukgen.domain.board.repository;

import com.example.mukgen.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByCreateAtGreaterThan(LocalDateTime dateTime);

    @Query(value = "SELECT * FROM tbl_board WHERE WEEK(create_at) = ?1", nativeQuery = true)
    List<Board> findByWeek(int week);
}
