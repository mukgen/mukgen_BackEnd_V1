package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.review.controller.dto.response.ReviewMinimumResponse;
import com.example.mukgen.domain.review.controller.dto.response.ReviewRankResponse;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.controller.dto.response.ReviewResponse;
import com.example.mukgen.domain.review.controller.dto.response.ReviewResponseList;
import com.example.mukgen.domain.review.repository.ReviewRepository;
import com.example.mukgen.domain.rice.service.exception.MealNotFoundException;
import com.example.mukgen.domain.review.service.exception.ReviewAlreadyExistsException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final RiceRepository riceRepository;

    private final ReviewRepository reviewRepository;

    private final UserFacade userFacade;

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    @Transactional
    public void addReview(
            ReviewCreateRequest request,
            int mealId
    ){

        Rice rice = riceRepository.findById(mealId)
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
        Rice rice = riceRepository.findById(mealId)
                .orElseThrow(()-> MealNotFoundException.EXCEPTION);

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

    public ReviewRankResponse findRankReview(){

        String jpql = "SELECT e FROM tbl_user e ORDER BY SIZE(e.reviewList) DESC";

        List<User> userSortList = entityManager.createQuery(jpql, User.class).setMaxResults(3).getResultList();

        List<ReviewMinimumResponse> reviewMinimumResponseList = userSortList.stream()
                .map(ReviewMinimumResponse::of).toList();

        return ReviewRankResponse.builder()
                .reviewMinimumResponseList(reviewMinimumResponseList)
                .build();
    }


}
