package com.example.mukgen.domain.board.controller;

import com.example.mukgen.domain.board.controller.dto.request.LikeCreateRequest;
import com.example.mukgen.domain.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikesController {

    private final LikeService likeService;

    @PostMapping
    public Integer likeAdd(
            @RequestBody
            LikeCreateRequest request
    ){
       return likeService.addLike(request);
    }

}
