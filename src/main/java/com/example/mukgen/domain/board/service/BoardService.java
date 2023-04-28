package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardResponse;
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
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        board.updateBoard(request.getTitle(), request.getContent());
    }

    public BoardListResponse findAll(){
        List<BoardResponse> boardResponses = boardRepository.findAll().stream()
                .map(it -> BoardResponse.builder()
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
}
