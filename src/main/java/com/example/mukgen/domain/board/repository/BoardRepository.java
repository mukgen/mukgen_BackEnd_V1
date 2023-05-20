package com.example.mukgen.domain.board.repository;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findAllByCreatedAtGreaterThan(LocalDateTime dateTime, Sort sort, Pageable pageable);

    Page<Board> findAllByUser(User user, Pageable pageable);

    @Query(value = "SELECT * FROM tbl_board WHERE WEEK(created_at) = ?1 and is_deleted = false ORDER BY created_at DESC", nativeQuery = true)
    Page<Board> findByWeek(int week, Pageable pageable);
}
