package com.example.mukgen.domain.user.controller;

import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.service.BoardService;
import com.example.mukgen.domain.review.controller.dto.response.ReviewListResponse;
import com.example.mukgen.domain.review.service.ReviewService;
import com.example.mukgen.domain.user.controller.response.UserInfoResponse;
import com.example.mukgen.domain.user.service.UserService;
import com.example.mukgen.infra.s3.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    public final S3Util s3Util;

    private final UserService userService;

    private final ReviewService reviewService;

    private final BoardService boardService;

    @GetMapping("/profile")
    public UserInfoResponse userDetails(){

        return userService.findUser();
    }

    @PostMapping("/profile/upload")
    public String uploadFile(
            @RequestParam("images")MultipartFile multipartFile
            ) throws IOException {

        return userService.profileUpload(multipartFile);

    }

    @GetMapping("/board")
    public BoardListResponse userBoardList(){
        return boardService.findMyBoard();
    }

    @GetMapping("/review")
    public ReviewListResponse userReviewList(){
        return reviewService.findMyReview();
    }

}
