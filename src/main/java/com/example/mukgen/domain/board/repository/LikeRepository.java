package com.example.mukgen.domain.board.repository;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {

    void removeByBoardAndUserName(Board board, String userName);

    boolean existsByBoardAndUserName(Board board,String userName);

    Optional<Likes> findByUserName(String userName);
}
