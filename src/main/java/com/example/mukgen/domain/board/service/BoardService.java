package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMaximumResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMinimumResponse;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.board.service.exception.BoardNotFoundException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserFacade userFacade;

    @Transactional
    public BoardListResponse addBoard(
            BoardCreateRequest request
    ) {
        User curUser = userFacade.currentUser();

        boardRepository.save(
                Board.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .user(curUser)
                        .build()
        );
<<<<<<< HEAD

=======
>>>>>>> develop
        return findAllBoard();
    }

    @Transactional
<<<<<<< HEAD
    public BoardMaximumResponse modifyBoard(
=======
    public BoardResponse modifyBoard(
>>>>>>> develop
            BoardUpdateRequest request,
            Long boardId
    ){

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        board.updateBoard(request.getTitle(), request.getContent());

<<<<<<< HEAD
        return onlyFindBoard(boardId);
    }
    @Transactional
    public BoardMaximumResponse onlyFindBoard(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        return BoardMaximumResponse.of(board);
=======
        return findBoard(boardId);
>>>>>>> develop
    }

    public BoardListResponse findAllBoard(){
        List<BoardMinimumResponse> boardMinimumResponseList = boardRepository.findAll().stream()
                .map(BoardMinimumResponse::of)
                .toList();

        return BoardListResponse.builder()
                .boardMinimumResponseList(boardMinimumResponseList)
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
    public BoardMaximumResponse findBoard(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        board.addViewCount();
        return BoardMaximumResponse.of(board);
    }

}