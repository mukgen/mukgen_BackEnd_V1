package com.example.mukgen.domain.like.entity;

import com.example.mukgen.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    public Likes(Board board, String userName) {
        this.board = board;
        this.userName = userName;
    }
}
