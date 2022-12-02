package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizResultServiceImpl implements QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private static final int PERCENT_COEFFICIENT = 20;

    @Transactional
    @Override
    public void create(List<EmotionDto> emotions) {
        Long resultId = quizResultRepository.getNextRowId();
        emotions.forEach(dto -> quizResultRepository.save(QuizResult.builder()
                .id(resultId)
                .quiz(null)
                .user(null)
                .emotion(new Emotion(dto.getEmotionId(), null))
                .score(dto.getValue())
                .createdAt(LocalDateTime.now())
                .build()));
    }

    @Override
    public Map<Long, Double> findAll(Long resultId) {
        return quizResultRepository.findAllById(resultId).stream()
                .collect(Collectors.groupingBy(res -> res.getEmotion().getId(), LinkedHashMap::new,
                        Collectors.averagingInt(res -> res.getScore() * PERCENT_COEFFICIENT)));
    }
}
