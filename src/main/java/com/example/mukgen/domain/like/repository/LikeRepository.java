package com.example.mukgen.domain.like.repository;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {

    void removeByBoardAndUserName(Board board, String username);

    boolean existsByBoardAndUserName(Board board,String username);

    Optional<Likes> findByUserName(String username);
}
