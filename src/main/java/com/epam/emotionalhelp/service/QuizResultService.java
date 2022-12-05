package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;

import java.util.List;

public interface QuizResultService {
    void calculateResult(Long quizId, List<EmotionDto> emotions);
}
