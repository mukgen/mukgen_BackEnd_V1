package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikesController {

    private final LikeService likeService;

    @PostMapping
    public Integer likeAdd(
            @PathVariable Long boardId
    ){
       return likeService.addLike(boardId);
    }

}
