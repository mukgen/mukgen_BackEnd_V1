package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMaximumResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMinimumResponse;
import com.example.mukgen.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

<<<<<<< HEAD
    private final BoardService boardService;

    @PostMapping
    public void boardAdd(
            @RequestBody
            BoardCreateRequest request
    ){
        boardService.addBoard(request);
    }

    @PutMapping("/{boardId}")
    public BoardMaximumResponse boardModify(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateRequest request
    ){
        return boardService.modifyBoard(request,boardId);
    }

    @GetMapping("/list")
    public BoardListResponse boardList() {
        return boardService.findAllBoard();
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
=======
>>>>>>> develop
}