package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.dto.EmotionDto;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/results")
@RequiredArgsConstructor
public class QuizResultController {
    private QuizResultService quizResultService;

    @PostMapping
    public Map<Long, Integer> calculateResult(@RequestBody List<EmotionDto> emotions) {
        return quizResultService.calculateResult(emotions);
    }

}
