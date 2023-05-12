package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.review.controller.dto.response.*;
import com.example.mukgen.domain.review.service.exception.ReviewNotFoundException;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.repository.ReviewRepository;
<<<<<<< HEAD
import com.example.mukgen.domain.rice.service.exception.RiceNotFoundException;
=======
import com.example.mukgen.domain.rice.service.RiceService;
>>>>>>> review
import com.example.mukgen.domain.review.service.exception.ReviewAlreadyExistsException;
import com.example.mukgen.domain.rice.service.exception.MealNotFoundException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.time.LocalDate;
=======
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
>>>>>>> review
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final RiceRepository riceRepository;

    private final ReviewRepository reviewRepository;

    private final UserFacade userFacade;

    private final RiceService riceService;

    private final EntityManager entityManager;

    @Transactional
    public void addReview(
            ReviewCreateRequest request,
            int mealId
    ){

        int tempMealId = mealId;

        int day = (tempMealId/=10) % 100; // 12

        LocalDate curDate = LocalDate.now();
        int curId = (curDate.getYear()*10000 + curDate.getMonthValue()*100 + curDate.getDayOfMonth())*10;

        if(day!=curDate.getDayOfMonth()){
            throw new IllegalStateException("오류 시발!");
        }

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

    public ReviewMaximumResponse findReview(
            Long reviewId
    ){
<<<<<<< HEAD
        Rice rice = riceRepository.findById(mealId)
                .orElseThrow(()-> RiceNotFoundException.EXCEPTION);
=======
>>>>>>> review

        return ReviewMaximumResponse.builder()
                .content(review.getReview())
                .userName(review.getUser().getName())
                .count(review.getCount())
                .build();
    }

    public ReviewResponseList findReview(int riceId){

        Rice rice = riceRepository.findById(riceId)
                .orElseThrow(()->  MealNotFoundException.EXCEPTION);

        List<ReviewResponse> reviewResponseList = reviewRepository.findAllByRice(rice)
                .stream().map(ReviewResponse::of).toList();

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

    public ReviewRankResponseList findRankReview(){

        String jpql = "SELECT e FROM tbl_user e ORDER BY SIZE(e.reviewList) DESC";

        List<User> userSortList = entityManager.createQuery(jpql, User.class).setMaxResults(3).getResultList();

        List<ReviewRankResponse> reviewRankResponseList = userSortList.stream()
                .map(ReviewRankResponse::of).toList();

        return ReviewRankResponseList.builder()
                .reviewRankResponseList(reviewRankResponseList)
                .build();
    }

    public ReviewTodayListResponse findTodayReview(){

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int riceId = Integer.parseInt(currentDate.format(formatter))*10;

        var reviewResponseLists = List.of(
                findReview(riceId + 1),
                findReview(riceId + 2),
                findReview(riceId + 3)
        );

        return ReviewTodayListResponse.builder()
                .reviewResponseLists(reviewResponseLists)
                .build();

    }


}
