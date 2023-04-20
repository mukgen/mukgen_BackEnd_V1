package com.example.mukgen.domain.board.entity;

import com.example.mukgen.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    private String content;

    private int likeCount;

    private int view;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @JoinColumn(name = "user_id")
    private String userName;


    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
