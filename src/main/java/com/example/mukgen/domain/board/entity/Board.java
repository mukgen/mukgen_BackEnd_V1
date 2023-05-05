package com.example.mukgen.domain.board.entity;

import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.like.entity.Likes;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int likeCount;

    private int viewCount;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "board_id")
    private List<Likes> likesList = new ArrayList<>();

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateAt = LocalDateTime.now();
    }

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.likeCount = 0;
        this.viewCount = 0;
        this.createAt = LocalDateTime.now();
    }

    public void addLike(){
        this.likeCount++;
    }
    public void removeLike(){
        this.likeCount--;
    }

    public void addViewCount(){
        this.viewCount++;
    }
}
