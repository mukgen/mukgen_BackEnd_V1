package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.response.LikeClickResponse;
import com.example.mukgen.domain.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikesController {

    private final LikeService likeService;

    @PostMapping("/{boardId}")
    public LikeClickResponse likeAdd(
            @PathVariable Long boardId
    ){
       return likeService.addLike(boardId);
    }

}
