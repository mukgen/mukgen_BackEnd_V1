package com.example.mukgen.domain.boardcomment.entity;

import com.example.mukgen.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tbl_boardComment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardComment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String writer;

    private LocalDateTime createAt;

}
