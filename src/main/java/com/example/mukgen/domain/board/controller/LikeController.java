package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.response.LikeClickResponse;
import com.example.mukgen.domain.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/mukgen/like")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeClickResponse likeAdd(
            @PathVariable Long boardId
    ){
       return likeService.addLike(boardId);
    }

}
