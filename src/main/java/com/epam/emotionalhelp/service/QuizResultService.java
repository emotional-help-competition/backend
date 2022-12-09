package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.QuizResultEmotionDto;
import com.epam.emotionalhelp.model.QuizResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizResultService {
    Page<QuizResult> findAll(Pageable pageable);

    int calculate(Long quizId, List<EmotionDto> emotions);

    List<QuizResultEmotionDto> findAllByAttemptId(Long attemptId);
}
