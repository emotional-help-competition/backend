package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/recommendations")
@RequiredArgsConstructor
@Validated
public class RecommendationController {
    private final RecommendationRepository recommendationRepository;

    @PostMapping
    public List<RecommendationEntity> getAll(@RequestBody @Valid List<EmotionDto> emotions) {
        return emotions.stream()
                .flatMap(em -> recommendationRepository.findAllByEmotionId(em.getEmotionId()).stream()
                        .filter(rec -> isInsideInterval(em, rec)))
                .collect(Collectors.toList());
    }

    private boolean isInsideInterval(EmotionDto em, RecommendationEntity rec) {
        return rec.getFloor() <= em.getValue() && em.getValue() <= rec.getCeil();
    }

    @GetMapping("/{id}")
    public RecommendationEntity foo(@PathVariable Long id) {
        return recommendationRepository.findAllByEmotionId(id).get(0);
    }
}
