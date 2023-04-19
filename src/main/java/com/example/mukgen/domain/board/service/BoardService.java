package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
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
                Board.builder()
                    .userName(curUser.getName())
                    .content(request.getContent())
                    .title(request.getTitle())
                    .createAt(LocalDateTime.now())
                    .view(0)
                    .likeCount(0)
                    .build()
        );
    }

    @Transactional
    public void updateBoard(
            BoardUpdateRequest request,
            Long boardId
    ){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 엔티티 입니다."));

        board.updateBoard(request.getTitle(), request.getContent());
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }
}
