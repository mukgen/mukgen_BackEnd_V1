package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.response.LikeCountResponse;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.board.service.exception.BoardNotFoundException;
import com.example.mukgen.domain.board.entity.Likes;
import com.example.mukgen.domain.board.repository.LikeRepository;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikeService {

    private final UserFacade userFacade;

    private final LikeRepository likeRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public LikeCountResponse addLike(Long boardId){

        User curUser = userFacade.currentUser();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> BoardNotFoundException.EXCEPTION);

        if(likeRepository.existsByBoardAndUserName(board,curUser.getName())){
            board.removeLike();
            likeRepository.removeByBoardAndUserName(board,curUser.getName());
        }
        else{
            Likes like = new Likes(board,curUser.getName());
            likeRepository.save(like);
            board.addLike();
        }

        return  LikeCountResponse.builder()
                    .boardLike(board.getLikeCount())
                    .build();
    }
}
