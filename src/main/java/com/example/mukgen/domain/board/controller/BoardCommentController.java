package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.BoardCommentCreateRequest;
import com.example.mukgen.domain.board.controller.dto.request.BoardCommentUpdateRequest;
import com.example.mukgen.domain.board.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/board-comment")
@RestController
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void boardCommentAdd(
            @RequestBody @Valid BoardCommentCreateRequest request
            ){
        boardCommentService.addBoardComment(request);
    }

    @DeleteMapping("/{boardCommentId}")
    public void boardCommentRemove(
            @PathVariable Long boardCommentId
    ){
        boardCommentService.removeBoardComment(boardCommentId);
    }

    @PutMapping("/{boardCommentId}")
    public void boardCommentModify(
            @RequestBody @Valid BoardCommentUpdateRequest request,
            @PathVariable Long boardCommentId
            ){
        boardCommentService.modifyBoardComment(boardCommentId,request);
    }

}
