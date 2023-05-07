package com.example.mukgen.domain.review.controller;

import com.example.mukgen.domain.review.entity.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.entity.dto.response.ReviewResponseList;
import com.example.mukgen.domain.review.service.ReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{mealId}")
    public void reviewAdd(
            @PathVariable int mealId,
            @RequestBody ReviewCreateRequest request
    ){
        reviewService.addReview(request,mealId);
    }

    @GetMapping("/{mealId}")
    public ReviewResponseList reviewDetails(
            @PathVariable int mealId
    ){
        return reviewService.findReview(mealId);
    }

    @GetMapping
    public ReviewResponseList reviewList(){
        return reviewService.findAllReview();
    }

}
