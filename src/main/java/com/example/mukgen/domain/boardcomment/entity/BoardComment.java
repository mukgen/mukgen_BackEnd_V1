package com.example.mukgen.domain.boardcomment.entity;

import com.example.mukgen.domain.BaseTimeEntity;
import com.example.mukgen.domain.board.entity.Board;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tbl_board_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE `tbl_board_comment` SET is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class BoardComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "writer")
    private String writer;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    public void updateBoardComment(String content){
        this.content = content;
    }

}
