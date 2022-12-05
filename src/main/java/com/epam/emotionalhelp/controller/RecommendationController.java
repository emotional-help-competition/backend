package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/recommendations")
@RequiredArgsConstructor
@Validated
public class RecommendationController {
    private final RecommendationRepository recommendationRepository;

    @PostMapping
    public List<RecommendationEntity> getAll(@RequestBody @Valid List<EmotionDto> emotions) {
        return emotions.stream()
                .map(em -> {
                    // could be a few
                    RecommendationEntity recommendation = recommendationRepository.findByEmotionId(em.getEmotionId());
                    Integer value = em.getValue();
                    if (value < recommendation.getFloor() || recommendation.getCeil() < value) {
                        return null;
                    }
                    return recommendation;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecommendationEntity foo(@PathVariable Long id) {
        return recommendationRepository.findByEmotionId(id);
    }
}
