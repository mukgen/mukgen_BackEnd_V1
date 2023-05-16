package com.example.mukgen.domain.review.controller;

import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.controller.dto.response.*;
import com.example.mukgen.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{mealId}")
    public void reviewAdd(
            @PathVariable int mealId,
            @RequestBody @Valid ReviewCreateRequest request
    ){
        reviewService.addReview(request,mealId);
    }

    @GetMapping("/{reviewId}")
    public ReviewMaximumResponse reviewDetails(
            @PathVariable Long reviewId
    ){
        return reviewService.findReview(reviewId);
    }

    @GetMapping("/today")
    public ReviewTodayListResponse reviewTodayList(){
        return reviewService.findTodayReview();
    }

    @GetMapping("/rank")
    public ReviewRankResponseList reviewRankList(){
        return reviewService.findRankReview();
    }

    @GetMapping("/total")
    public ReviewMaximumListResponse reviewTotalList(){
        return reviewService.findUserReview();
    }



}
