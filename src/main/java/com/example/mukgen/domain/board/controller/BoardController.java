package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardResponse;
import com.example.mukgen.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void boardAdd(
            @RequestBody
            BoardCreateRequest request
    ){
        boardService.addBoard(request);
    }

    @PutMapping("/{boardId}")
    public void boardModify(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateRequest request
    ){
        boardService.modifyBoard(request,boardId);
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
    public BoardResponse boardDetails(
            @PathVariable Long boardId
    ){
        return boardService.findBoard(boardId);
    }
}