package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.dto.EmotionDto;

import java.util.List;
import java.util.Map;

public interface QuizResultService {
    Map<Long, Integer> calculateResult(List<EmotionDto> emotions);
}
