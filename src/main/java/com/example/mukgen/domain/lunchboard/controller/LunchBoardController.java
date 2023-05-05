package com.example.mukgen.domain.lunchboard.controller;

import com.example.mukgen.domain.lunchboard.controller.dto.request.LunchBoardCreateRequest;
import com.example.mukgen.domain.lunchboard.controller.dto.request.LunchBoardUpdateRequest;
import com.example.mukgen.domain.lunchboard.service.LunchBoardService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

public class LunchBoardController {

    private LunchBoardService lunchBoardService;
    @PostMapping
    public void createLunchBoard(
            @RequestBody LunchBoardCreateRequest request
    ) {
        lunchBoardService.createLunchBoard(request);
    }

    @PutMapping("/{boardId}")
    public void updateLunchBoard(
            @RequestBody LunchBoardUpdateRequest request,
            @PathVariable Long boardId
    ) {
        lunchBoardService.updateLunchBoard(request, boardId);
    }

    @DeleteMapping("/{boardId}")
    public void deleteLunchBoard(
            @PathVariable Long boardId
    ) {
        lunchBoardService.deleteLunchBoard(boardId);
    }
}