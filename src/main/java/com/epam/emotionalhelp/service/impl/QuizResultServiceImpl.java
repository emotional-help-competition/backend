package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.service.EmotionService;
import com.epam.emotionalhelp.service.QuizResultService;
import com.epam.emotionalhelp.service.QuizService;
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
    private final EmotionService emotionService;
    private final QuizService quizService;
    private static final int percentCoefficient = 20;

    @Transactional
    @Override
    public Map<Long, Double> calculateResult(List<EmotionDto> emotions) {
        return emotions.stream()
                .peek(em -> {
                    QuizResult quizResult = QuizResult.builder()
                            .quiz(null)
                            .user(null)
                            .emotion(new Emotion(em.getEmotionId(), null))
                            .score(em.getValue())
                            .createdAt(LocalDateTime.now())
                            .build();
                    quizResultRepository.saveAndFlush(quizResult);
                })
                .collect(Collectors.groupingBy(EmotionDto::getEmotionId, LinkedHashMap::new,
                        Collectors.averagingInt(emotionDto -> emotionDto.getValue() * percentCoefficient)));
    }
}
