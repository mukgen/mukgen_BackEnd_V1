package com.example.mukgen.domain.board.entity;

import com.example.mukgen.global.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `tbl_likes` SET is_deleted = true where id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "tbl_likes")
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Builder
    public Like(Board board, String userName) {
        this.board = board;
        this.userName = userName;
    }

}
