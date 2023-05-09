package com.example.mukgen.domain.like.controller;

import com.example.mukgen.domain.like.controller.dto.request.LikeCreateRequest;
import com.example.mukgen.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
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
