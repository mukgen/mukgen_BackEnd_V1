package com.example.mukgen.domain.boardcomment.repository;

import com.example.mukgen.domain.boardcomment.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment,Long> {
}
