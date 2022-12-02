package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;

import java.util.List;
import java.util.Map;

public interface QuizResultService {
    void create(List<EmotionDto> emotions);
    Map<Long, Double> findAll(Long resultId);
}
