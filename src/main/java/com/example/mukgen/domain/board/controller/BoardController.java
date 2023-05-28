package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMaximumResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardPopularListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardTabListResponse;
import com.example.mukgen.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void boardAdd(
            @RequestBody @Valid BoardCreateRequest request
    ){
        boardService.addBoard(request);
    }

    @PutMapping("/{boardId}")
    public BoardMaximumResponse boardModify(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardUpdateRequest request
    ){
        return boardService.modifyBoard(request,boardId);
    }


    @DeleteMapping("/{boardId}")
    public void boardRemove(
            @PathVariable Long boardId
    ){
        boardService.deleteBoard(boardId);
    }

    @GetMapping("/{boardId}")
    public BoardMaximumResponse boardDetails(
            @PathVariable Long boardId
    ){
        return boardService.findBoard(boardId);
    }

    @GetMapping("/hot")
    public BoardPopularListResponse boardPopularList(){
        return boardService.findPopularBoard();
    }

    @GetMapping("/day")
    public BoardTabListResponse boardDayList(){
        return boardService.findDayBoard();
    }

    @GetMapping("/week")
    public BoardTabListResponse boardWeekList(){
        return boardService.findWeekBoard();
    }

    @GetMapping("/total")
    public BoardTabListResponse boardTotalList(){
        return boardService.findAllBoard();
    }


}