package com.example.mukgen.domain.boardcomment.controller;

import com.example.mukgen.domain.boardcomment.controller.dto.request.BoardCommentCreateRequest;
import com.example.mukgen.domain.boardcomment.controller.dto.request.BoardCommentUpdateRequest;
import com.example.mukgen.domain.boardcomment.controller.dto.response.BoardCommentResponse;
import com.example.mukgen.domain.boardcomment.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boardComment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping
    public void boardCommentAdd(
            @RequestBody BoardCommentCreateRequest request
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
            @RequestBody BoardCommentUpdateRequest request,
            @PathVariable Long boardCommentId
            ){
        boardCommentService.modifyBoardComment(boardCommentId,request);
    }

}
