package com.example.mukgen.domain.board.entity;

import com.example.mukgen.domain.boardcomment.entity.BoardComment;
import com.example.mukgen.domain.like.entity.Likes;
import com.example.mukgen.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_board")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `tbl_board` SET is_deleted = true where id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "content", nullable = false, length = 300)
    private String content;

    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;

    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    @Column(name = "comment_count", nullable = false)
    private int commentCount = 0;

    @Column(name = "is_updated")
    private Boolean isUpdated = false;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardComment> boardCommentList = new ArrayList<>();

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateAt = LocalDateTime.now();
        this.isUpdated = true;
    }

    public void addLike(){
        this.likeCount++;
    }

    public void addCommentCount(){
        this.commentCount++;
    }

    public void removeCommentCount(){
        this.commentCount--;
    }

    public void removeLike(){
        this.likeCount--;
    }

    public void addViewCount(){
        this.viewCount++;
    }

    @Builder
    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.likeCount = 0;
        this.viewCount = 0;
        this.createAt = LocalDateTime.now();
    }

    @Builder
    public Board(Long id, String title, String content, int likeCount,
            int viewCount, User user, List<Likes> likesList
            , List<BoardComment> boardCommentList
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.user = user;
        this.likesList = likesList;
        this.boardCommentList = boardCommentList;
    }
}

