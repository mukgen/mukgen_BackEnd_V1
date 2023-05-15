package com.example.mukgen.domain.boardcomment.service;

import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.board.service.exception.BoardNotFoundException;
import com.example.mukgen.domain.boardcomment.controller.dto.request.BoardCommentCreateRequest;
import com.example.mukgen.domain.boardcomment.controller.dto.request.BoardCommentUpdateRequest;
import com.example.mukgen.domain.boardcomment.entity.BoardComment;
import com.example.mukgen.domain.boardcomment.repository.BoardCommentRepository;
import com.example.mukgen.domain.boardcomment.service.exception.BoardCommentNotFoundException;
import com.example.mukgen.domain.boardcomment.service.exception.BoardCommentWriterMissMatchException;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    private final BoardRepository boardRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addBoardComment(
            BoardCommentCreateRequest request
    ){

        Board board = boardRepository.findById(request.getBoardId())
                        .orElseThrow(()-> BoardNotFoundException.EXCEPTION);

        BoardComment comment = BoardComment.builder()
                .isDeleted(false)
                .board(board)
                .content(request.getContent())
                .writer(userFacade.currentUser().getName())
                .build();

         board.addCommentCount();

        boardCommentRepository.save(comment);
    }

    @Transactional
    public void removeBoardComment(
        Long boardCommentId
    ){

        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> BoardCommentNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().getAccountId().equals(boardComment.getWriter())){
            throw BoardCommentWriterMissMatchException.EXCEPTION;
        }

        boardComment.getBoard().removeCommentCount();

        boardCommentRepository.deleteById(boardCommentId);

    }

    @Transactional
    public void modifyBoardComment(
            Long boardCommentId,
            BoardCommentUpdateRequest request
    ){
        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> BoardCommentNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().getAccountId().equals(boardComment.getWriter())){
            throw BoardCommentWriterMissMatchException.EXCEPTION;
        }

        boardComment.updateBoardComment(request.getContent());
    }
}
