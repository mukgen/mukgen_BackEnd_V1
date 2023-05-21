package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealSuggestionScheduledService {

    private final MealSuggestionRepository mealSuggestionRepository;

    @Scheduled(cron = "0 0 0 * *")
    @Transactional
    public void autoRemoveMealSuggestion() {
        mealSuggestionRepository
                .removeByCreatedAtEquals(
                        LocalDateTime.now().minusWeeks(1));
    }
}
