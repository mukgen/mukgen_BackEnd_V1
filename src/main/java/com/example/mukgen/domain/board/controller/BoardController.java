package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.BoardCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardUpdateRequest;
import com.example.mukgen.domain.board.entity.Board;
import com.example.mukgen.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void createBoard(
            @RequestBody
            BoardCreateRequest request
    ){
        boardService.createBoard(request);
    }

    @PutMapping("/{boardId}")
    public void updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardUpdateRequest request
    ){
        boardService.updateBoard(request,boardId);
    }

    @GetMapping("/list")
    public List<Board> findAll(){
        return boardService.findAll();
    }
}
