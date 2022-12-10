package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.AttemptDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.model.QuizResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizResultService {
    Page<QuizResult> findAll(Pageable pageable);

    AttemptDto calculate(Long quizId, List<EmotionDto> emotions);

    List<EmotionalMapDto> findQuizResultsByAttemptId(Long id);
}
