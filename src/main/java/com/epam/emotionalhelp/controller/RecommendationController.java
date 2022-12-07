package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/v1/recommendations")
@RequiredArgsConstructor
@Validated
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/attempt/{attemptId}")
    public List<RecommendationEntity> findAllByAttemptId(@Min(1) @PathVariable Long attemptId) {
        return recommendationService.findAllByAttemptId(attemptId);
    }

    @GetMapping("/{emotionId}")
    public List<RecommendationEntity> findAllByEmotionId(@Min(1) @PathVariable Long emotionId) {
        return recommendationService.findAllByEmotionId(emotionId);
    }
}
