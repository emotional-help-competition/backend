package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface QuizResultService {
    Page<QuizResult> findAll(Pageable pageable);

    int calculate(Long quizId, List<EmotionDto> emotions);

    Map<Emotion, List<Subcategory>> findQuizResultByAttemptId(Long id);
}
