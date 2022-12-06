package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizResultService {
    Page<QuizResult> findAll(Pageable pageable);

    List<List<Subcategory>> calculateResult(Long quizId, List<EmotionDto> emotions);
}
