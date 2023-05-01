package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardResponse;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserFacade userFacade;

    @Transactional
    public void createBoard(
            BoardCreateRequest request
    ) {
        User curUser = userFacade.currentUser();
        boardRepository.save(
                new Board(request.getTitle(), request.getContent(), curUser)
        );
    }

    @Transactional
    public void updateBoard(
            BoardUpdateRequest request,
            Long boardId
    ){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("엔티티을 찾을 수 없습니다."));

        board.updateBoard(request.getTitle(), request.getContent());
    }

    public BoardListResponse findAllBoard(){
        List<BoardResponse> boardResponses = boardRepository.findAll().stream()
                .map(it -> BoardResponse.builder()
                        .likeResponseList(it.getLikesList()
                                .stream()
                                .map(it1 -> LikeResponse.builder()
                                        .boardId(it1.getBoard().getId())
                                        .username(it1.getUserName()).build()).toList())
                        .title(it.getTitle())
                        .content(it.getContent())
                        .username(it.getUser().getName())
                        .createAt(it.getCreateAt())
                        .updateAt(it.getUpdateAt())
                        .likeCount(it.getLikeCount())
                        .viewCount(it.getViewCount())
                        .build())
                .toList();

        return BoardListResponse.builder()
                .boardResponseList(boardResponses)
                .build();
    }


    @Transactional
    public void deleteBoard(
            Long boardId
    ){
        if(!boardRepository.existsById(boardId)){
            throw BoardNotFoundException.EXCEPTION;
        }
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public BoardResponse findOne(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        board.addViewCount();
        return BoardResponse.builder()
                .likeResponseList(board.getLikesList()
                        .stream()
                        .map(it -> LikeResponse.builder()
                                .boardId(it.getBoard().getId())
                                .username(it.getUserName()).build()).toList())
                .content(board.getContent())
                .title(board.getTitle())
                .username(board.getUser().getName())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .createAt(board.getCreateAt())
                .updateAt(board.getUpdateAt())
                .build();
    }

}