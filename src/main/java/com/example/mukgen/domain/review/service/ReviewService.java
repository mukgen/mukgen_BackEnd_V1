package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.controller.dto.response.ReviewResponse;
import com.example.mukgen.domain.review.controller.dto.response.ReviewResponseList;
import com.example.mukgen.domain.review.repository.ReviewRepository;
import com.example.mukgen.domain.rice.service.exception.RiceNotFoundException;
import com.example.mukgen.domain.review.service.exception.ReviewAlreadyExistsException;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final RiceRepository riceRepository;

    private final ReviewRepository reviewRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addReview(
            ReviewCreateRequest request,
            int mealId
    ){

        Rice rice = riceRepository.findById(mealId)
                .orElseThrow(()-> RiceNotFoundException.EXCEPTION);

        if(reviewRepository.existsByRiceAndUser(rice,userFacade.currentUser())){
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        Review review = Review.builder()
                .user(userFacade.currentUser())
                .rice(rice)
                .count(request.getCount())
                .review(request.getReview())
                .build();

        reviewRepository.save(review);
    }

    public ReviewResponseList findReview(
            int mealId
    ){
        Rice rice = riceRepository.findById(mealId)
                .orElseThrow(()-> RiceNotFoundException.EXCEPTION);

        List<ReviewResponse> reviewResponseList =
                reviewRepository.findAllByRice(rice)
                        .stream()
                        .map(ReviewResponse::of)
                                .toList();
        return ReviewResponseList.builder()
                .reviewResponseList(reviewResponseList)
                .build();
    }

    public ReviewResponseList findAllReview(){
        List<ReviewResponse> reviewResponseList =
            reviewRepository.findAllByUser(userFacade.currentUser())
                    .stream()
                    .map(ReviewResponse::of)
                    .toList();
        return ReviewResponseList.builder()
                .reviewResponseList(reviewResponseList)
                .build();
    }
}
