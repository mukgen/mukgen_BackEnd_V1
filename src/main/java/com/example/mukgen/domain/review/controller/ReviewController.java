package com.example.mukgen.domain.review.controller;

import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.controller.dto.response.*;
import com.example.mukgen.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{mealId}")
    public void reviewAdd(
            @PathVariable int mealId,
            @RequestPart("request") @Valid ReviewCreateRequest request,
            @RequestPart(value = "images", required = false) MultipartFile multipartFile
    ) throws IOException {
        if(multipartFile != null) {
            reviewService.addReview(request, mealId, multipartFile);
        } else{
            reviewService.addReview(request, mealId);
        }
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
    public ReviewRankListResponse reviewRankList(){
        return reviewService.findRankReview();
    }

    @GetMapping("/total")
    public ReviewMaximumListResponse reviewTotalList(){
        return reviewService.findUserReview();
    }

}
