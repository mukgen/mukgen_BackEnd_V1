package com.example.mukgen.domain.user.controller;

import com.example.mukgen.domain.board.controller.dto.response.BoardListResponse;
import com.example.mukgen.domain.board.controller.dto.response.BoardMaximumResponse;
import com.example.mukgen.domain.board.service.BoardService;
import com.example.mukgen.domain.review.controller.dto.response.ReviewResponseList;
import com.example.mukgen.domain.review.service.ReviewService;
import com.example.mukgen.domain.user.controller.response.UserProfileResponse;
import com.example.mukgen.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ReviewService reviewService;

    private final BoardService boardService;

    @GetMapping("/profile/{userId}")
    public UserProfileResponse userDetails(
            @PathVariable Long userId
    ){
        return userService.findUser(userId);
    }

    @GetMapping("/board")
    public BoardListResponse userBoardList(){
        return boardService.findMyBoard();
    }

    @GetMapping("/review")
    public ReviewResponseList userReviewList(){
        return reviewService.findMyReview();
    }

}
