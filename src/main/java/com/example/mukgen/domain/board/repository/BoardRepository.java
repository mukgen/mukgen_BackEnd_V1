package com.example.mukgen.domain.board.repository;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByCreatedAtGreaterThan(LocalDateTime dateTime, Sort sort);

    List<Board> findAllByUser(User user);

    @Query(value = "SELECT * FROM tbl_board WHERE WEEK(created_at) = ?1 and is_deleted = false ORDER BY created_at DESC", nativeQuery = true)
    List<Board> findByWeek(int week);
}
