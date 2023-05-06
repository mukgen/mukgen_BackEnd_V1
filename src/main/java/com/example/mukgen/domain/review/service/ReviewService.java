package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.meal.entity.Rice;
import com.example.mukgen.domain.meal.repository.MealRepository;
import com.example.mukgen.domain.meal.service.MealService;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.entity.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.entity.dto.response.ReviewResponse;
import com.example.mukgen.domain.review.entity.dto.response.ReviewResponseList;
import com.example.mukgen.domain.review.repository.ReviewRepository;
import com.example.mukgen.domain.meal.service.exception.MealNotFoundException;
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

    private final MealService mealService;

    private final MealRepository mealRepository;

    private final ReviewRepository reviewRepository;

    private final UserFacade userFacade;

    @Transactional
    public void addReview(
            ReviewCreateRequest request,
            int mealId
    ){

        Rice rice = mealRepository.findById(mealId)
                .orElseThrow(()-> MealNotFoundException.EXCEPTION);

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
        Rice rice = mealRepository.findById(mealId)
                .orElseThrow(()-> MealNotFoundException.EXCEPTION);

        List<ReviewResponse> reviewResponseList =
                reviewRepository.findAllByRice(rice)
                        .stream()
                        .map(it -> ReviewResponse.builder()
                                .userName(it.getUser().getAccountId())
                                .content(it.getReview())
                                .count(it.getCount()).build())
                                .toList();
        return ReviewResponseList.builder()
                .reviewResponseList(reviewResponseList)
                .build();
    }
}
