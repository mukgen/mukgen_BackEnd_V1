package com.example.mukgen.domain.board.service;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.*;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.repository.BoardRepository;
import com.example.mukgen.domain.board.repository.LikeRepository;
import com.example.mukgen.domain.board.service.exception.BoardNotFoundException;
import com.example.mukgen.domain.board.service.exception.BoardWriterMissmatchException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final LikeRepository likeRepository;

    private final BoardRepository boardRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addBoard(
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

    }

    @Transactional
    public BoardMaximumResponse modifyBoard(
            BoardUpdateRequest request,
            Long boardId
    ){

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        board.updateBoard(request.getTitle(), request.getContent());

        return onlyFindBoard(boardId);
    }
    @Transactional
    public BoardMaximumResponse onlyFindBoard(Long boardId){

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        boolean isLiked = isLiked(board);

        return BoardMaximumResponse.of(board, isLiked);
    }

    public BoardTabListResponse findAllBoard(){
        List<BoardMinimumResponse> boardMinimumResponseList = boardRepository
                .findAll(Sort.by(Sort.Direction.DESC,"createdAt")).stream()
                .map(BoardMinimumResponse::of)
                .toList();

        BoardListResponse boardListResponse = BoardListResponse.builder()
                .boardMinimumResponseList(boardMinimumResponseList)
                .build();

        List<BoardPopularResponse> boardPopularResponseList = findPopularBoard().getBoardPopularResponseList();

        return BoardTabListResponse.builder()
                .boardListResponse(boardListResponse)
                .boardPopularListResponse(findPopularBoard())
                .build();
    }


    @Transactional
    public void deleteBoard(
            Long boardId
    ){

        Board board = boardRepository.findById(boardId)
                        .orElseThrow(()-> BoardNotFoundException.EXCEPTION);

        if(!userFacade.currentUser().equals(board.getUser())){

            throw BoardWriterMissmatchException.EXCEPTION;
        }
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public BoardMaximumResponse findBoard(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);
        board.addViewCount();
        return BoardMaximumResponse.of(board, isLiked(board));
    }

    public BoardPopularListResponse findPopularBoard(){

        int thisWeek = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());

        List<BoardPopularResponse> boardPopularResponseList =
                boardRepository.findAll(Sort.by(Sort.Direction.DESC, "viewCount"))
                        .stream()
                        .map(BoardPopularResponse::of)
                        .limit(3)
                        .toList();

        return BoardPopularListResponse.builder()
                .boardPopularResponseList(boardPopularResponseList)
                .build();
    }

    public BoardTabListResponse findDayBoard(){

        LocalDateTime curDateTime = LocalDateTime.now().minusDays(1);

        List<BoardMinimumResponse> boardMinimumResponseList =
                boardRepository.findAllByCreatedAtGreaterThan(curDateTime, Sort.by(Sort.Direction.DESC, "createdAt"))
                        .stream()
                        .map(BoardMinimumResponse::of)
                        .toList();

        BoardListResponse boardListResponse = BoardListResponse.builder()
                .boardMinimumResponseList(boardMinimumResponseList)
                .build();


        return BoardTabListResponse.builder()
                .boardListResponse(boardListResponse)
                .boardPopularListResponse(findPopularBoard())
                .build();
    }

    public BoardTabListResponse findWeekBoard(){

        int thisWeek = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());

        List<BoardMinimumResponse> boardMinimumResponseList =
                boardRepository.findByWeek(thisWeek).stream()
                        .map(BoardMinimumResponse::of).toList();

        return BoardTabListResponse.builder()
                .boardListResponse(BoardListResponse.builder()
                        .boardMinimumResponseList(boardMinimumResponseList)
                        .build())
                .boardPopularListResponse(findPopularBoard())
                .build();

    }

    public BoardListResponse findMyBoard(){

        User user = userFacade.currentUser();

        List<BoardMinimumResponse> boardMinimumResponseList = boardRepository.findAllByUser(user)
                .stream().map(BoardMinimumResponse::of).toList();

        return BoardListResponse.builder()
                .boardMinimumResponseList(boardMinimumResponseList)
                .build();

    }

    private boolean isLiked(Board board) {
        User user = userFacade.currentUser();

        boolean isLiked = likeRepository.existsByBoardAndUserName(board, user.getName());
        return isLiked;
    }

}