package com.example.mukgen.domain.mealsuggestion.service;

import com.example.mukgen.domain.mealsuggestion.repository.MealSuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class MealSuggestionScheduledService {

    private final MealSuggestionRepository mealSuggestionRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void autoRemoveMealSuggestion() {
        mealSuggestionRepository
                .removeAllByCreatedAtBefore(
                        LocalDateTime.now().minusWeeks(1));
    }
}
