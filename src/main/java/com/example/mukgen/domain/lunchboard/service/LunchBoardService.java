package com.example.mukgen.domain.lunchboard.service;

import com.example.mukgen.domain.lunchboard.entity.LunchBoard;
import com.example.mukgen.domain.lunchboard.repository.LunchBoardRepository;
import com.example.mukgen.domain.lunchboard.controller.dto.request.LunchBoardCreateRequest;
import com.example.mukgen.domain.user.service.UserFacade;
import com.example.mukgen.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LunchBoardService {

    private final LunchBoardRepository lunchBoardRepository;

    private final UserFacade userFacade;

    @Transactional
    public void createLunchBoard(
            LunchBoardCreateRequest request
    ) {
        User curuser = userFacade.currentUser();
        lunchBoardRepository.save(
                new LunchBoard(request.getTitle(), request.getContent(), curuser)
        );
    }
}