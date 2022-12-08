package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/attempt/{attemptId}")
    public List<AppointmentResponseDto> findAllByAttemptId(@Min(1) @PathVariable Long attemptId) {
        return recommendationService.findAllByAttemptId(attemptId);
    }
}
