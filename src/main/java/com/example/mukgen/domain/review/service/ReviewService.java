package com.example.mukgen.domain.review.service;

import com.example.mukgen.domain.review.controller.dto.request.ReviewCreateRequest;
import com.example.mukgen.domain.review.controller.dto.response.*;
import com.example.mukgen.domain.review.entity.Review;
import com.example.mukgen.domain.review.repository.ReviewRepository;
import com.example.mukgen.domain.review.service.exception.*;
import com.example.mukgen.domain.rice.entity.Rice;
import com.example.mukgen.domain.rice.repository.RiceRepository;
import com.example.mukgen.domain.rice.service.exception.RiceNotFoundException;
import com.example.mukgen.domain.rice.service.exception.RiceNotTodayException;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.infra.s3.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final S3Util s3Util;

    private final RiceRepository riceRepository;

    private final ReviewRepository reviewRepository;

    private final UserFacade userFacade;

    private final EntityManager entityManager;

    @Transactional
    public Long addReview(
            ReviewCreateRequest request,
            int mealId
    ){
        Rice rice = riceRepository.findById(mealId)
                .orElseThrow(()-> RiceNotFoundException.EXCEPTION);

        if(rice.getItem().equals("등록된 급식이 없습니다.")){
            throw RiceInaccessibleException.EXCEPTION;
        }

        ZonedDateTime curDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        Map<Integer, LocalTime> mealTimes = Map.of(
                1, LocalTime.of(8, 0),
                2, LocalTime.of(13, 10),
                3, LocalTime.of(18, 10)
        );

        LocalTime mealTime = mealTimes.get(mealId % 10);

        if (curDate.toLocalTime().isBefore(mealTime)) {
            throw ReviewYetTimeException.EXCEPTION;
        }

        int tempMealId = mealId;

        int day = (tempMealId/=10) % 100;

        int curId = (curDate.getYear()*10000 + curDate.getMonthValue()*100 + curDate.getDayOfMonth())*10;

        if(day!=curDate.getDayOfMonth()){
            throw RiceNotTodayException.EXCEPTION;
        }

        if(reviewRepository.existsByRiceAndUser(rice,userFacade.currentUser())){
            throw ReviewAlreadyExistsException.EXCEPTION;
        }

        Review review = Review.builder()
                .isDeleted(false)
                .user(userFacade.currentUser())
                .rice(rice)
                .count(request.getCount())
                .review(request.getReview())
                .month(curDate.getMonthValue())
                .day(curDate.getDayOfMonth())
                .build();

        reviewRepository.save(review);

        return review.getId();
    }

    @Transactional
    public void addReview(
            ReviewCreateRequest request,
            int mealId,
            MultipartFile multipartFile
    ) throws IOException {
        Long reviewId = addReview(request, mealId);
        imageUpload(reviewId, multipartFile);
    }

    public ReviewMaximumResponse findReview(
            Long reviewId
    ){

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->ReviewNotFoundException.EXCEPTION);

        return ReviewMaximumResponse.of(review);
    }

    public ReviewListResponse findReview(int riceId){

        Rice rice = riceRepository.findById(riceId)
                .orElseThrow(()->  RiceNotFoundException.EXCEPTION);

        List<ReviewResponse> reviewResponseList = reviewRepository.findAllByRice(rice)
                .stream().map(ReviewResponse::of).toList();

        return ReviewListResponse.builder()
                    .reviewResponseList(reviewResponseList)
                    .build();
    }


    public ReviewListResponse findMyReview(){
        List<ReviewResponse> reviewResponseList =
            reviewRepository.findAllByUser(userFacade.currentUser())
                    .stream()
                    .map(ReviewResponse::of)
                    .toList();
        return ReviewListResponse.builder()
                .reviewResponseList(reviewResponseList)
                .build();
    }

    public ReviewRankListResponse findRankReview(){

        String jpql = "SELECT e FROM tbl_user e ORDER BY SIZE(e.reviewList) DESC";

        List<User> userSortList = entityManager.createQuery(jpql, User.class).setMaxResults(3).getResultList();

        List<ReviewRankResponse> reviewRankResponseList = userSortList.stream()
                .map(ReviewRankResponse::of).toList();

        return ReviewRankListResponse.builder()
                .reviewRankResponseList(reviewRankResponseList)
                .build();
    }

    public ReviewTodayListResponse findTodayReview(){

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int riceId = Integer.parseInt(currentDate.format(formatter))*10;

        List<ReviewListResponse> review = List.of(
                findReview(riceId + 1),
                findReview(riceId + 2),
                findReview(riceId + 3)
        );

        List<ReviewResponse> result = new ArrayList<>();

        for(ReviewListResponse reviewListResponse : review){
            List<ReviewResponse> reviewResponseList = reviewListResponse.getReviewResponseList();
            for (ReviewResponse reviewResponse : reviewResponseList) {
               result.add(reviewResponse);
            }
        }



        return ReviewTodayListResponse.builder()
                .reviewResponseList(result)
                .build();

    }

    public  ReviewListResponse findDateReview(int date) {

        int riceId = date * 10;

        List<ReviewListResponse> review = List.of(
                findReview(++riceId),
                findReview(++riceId),
                findReview(++riceId)
        );

        List<ReviewResponse> reviewResponseList = new ArrayList<>();

        for (ReviewListResponse reviewListResponse : review) {
            reviewResponseList.addAll(reviewListResponse.getReviewResponseList());
        }

        return ReviewListResponse.builder()
                .reviewResponseList(reviewResponseList)
                .build();
    }

    public ReviewMaximumListResponse findUserReview(){

        List<ReviewMaximumResponse> reviewMaximumResponseList =
                reviewRepository.findAllByUser(userFacade.currentUser()).stream()
                        .map(ReviewMaximumResponse::of)
                        .toList();

        return ReviewMaximumListResponse.builder()
                .reviewMaximumResponseList(reviewMaximumResponseList)
                .build();

    }

    @Transactional
    public String imageUpload(Long reviewId,MultipartFile multipartFile) throws IOException {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> ReviewNotFoundException.EXCEPTION);

        User user = userFacade.currentUser();

        if(!review.getUser().equals(user)){
            throw ReviewWriterMismatchException.EXCEPTION;
        }

        String profileUrl = "";

        if(review.getImageUrl() != null && !review.getImageUrl().isEmpty()){
            profileUrl = review.getImageUrl();
            s3Util.deleteFile(profileUrl.split("/")[3]);
        }

        profileUrl = s3Util.upload(multipartFile);

        review.modifyImageUrl(profileUrl);

        return profileUrl;
    }

    public ReviewMaximumListResponse findAllReview(){

        LocalDateTime time = LocalDateTime.now().minusDays(7);

        List<ReviewMaximumResponse> reviewMaximumResponses = reviewRepository.findAllByCreatedAtGreaterThan(time, Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(ReviewMaximumResponse::of)
                .toList();

        return ReviewMaximumListResponse.builder()
                .reviewMaximumResponseList(reviewMaximumResponses)
                .build();

    }

    public ReviewStatisticsResponse reviewStatistics(){
        LocalDateTime now = LocalDateTime.now();
        List<Review> todayReview = reviewRepository.findAllByMonthAndDay(now.getMonthValue(), now.getDayOfMonth());
        return ReviewStatisticsResponse.fromReviewList(todayReview);
    }


}
