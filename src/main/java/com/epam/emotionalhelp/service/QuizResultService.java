package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;

import java.util.List;
import java.util.Map;

public interface QuizResultService {
    Map<Long, Double> calculateResult(List<EmotionDto> emotions);
}
