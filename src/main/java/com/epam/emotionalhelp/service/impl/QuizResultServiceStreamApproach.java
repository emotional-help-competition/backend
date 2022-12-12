package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.AttemptDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryDto;
import com.epam.emotionalhelp.model.QuizAttempt;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizAttemptRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
import com.epam.emotionalhelp.service.QuizResultService;
import com.epam.emotionalhelp.service.mapper.RecommendationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.epam.emotionalhelp.service.util.ExceptionSupplier.*;


/**
 * The type Quiz result service.
 */
@Primary
@Service
@RequiredArgsConstructor
public class QuizResultServiceStreamApproach implements QuizResultService {

    private static final int HEXAGONS_QUANTITY = 4;
    private static final int HEXAGON_CAPACITY = 3;
    private static final int PERCENT_COEFFICIENT = 100;
    private static final int SCORE_COEFFICIENT = 20;
    private static final long MAX_BIG_EMOTIONS_QUANTITY = 6L;

    private final QuizResultRepository quizResultRepository;
    private final EmotionRepository emotionRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;

    @Override
    public List<EmotionalMapDto> findQuizResultsByAttemptId(Long attemptId) {
        return quizResultRepository.findAllByAttemptId(attemptId).stream()
                .map(RecommendationMapper::toEmotionDto)
                .sorted(Comparator.comparing(EmotionDto::getValue).reversed())
                .limit(MAX_BIG_EMOTIONS_QUANTITY)
                .map(this::prepareEmotionalMapDto)
                .collect(Collectors.toList());
    }

    private EmotionalMapDto prepareEmotionalMapDto(EmotionDto emotionDto) {
        return EmotionalMapDto.builder()
                .category(getEmotion(emotionDto))
                .subCategories(getListsForEmotion(emotionDto))
                .build();
    }

    private List<SubcategoryDto> getListsForEmotion(EmotionDto emotion) {
        return splitToHexagons(subcategoryRepository.findAllSubcategories(emotion.getValue(), emotion.getEmotionId()))
                .stream()
                .map(this::getSubcategoryDto)
                .collect(Collectors.toList());
    }

    /**
     * We expect a list of subcategory with size <= hexagonsNumber * hexagonCapacity;
     * Example: 4 hexagon * 3 category;
     * 0 cat - 0 hex;
     * 1..3 cat - 1 hex;
     * 4..6 cat - 2 hex;
     * 7..9 cat - 3 hex;
     * 10..12 cat - 4 hex;
     */
    private List<List<Subcategory>> splitToHexagons(List<Subcategory> subcategories) {
        List<List<Subcategory>> complexList = new ArrayList<>();
        final int size = subcategories.size();
        int pivot = 0;
        for (int i = 0; i < HEXAGONS_QUANTITY; i++) {
            int newPivot = pivot + HEXAGON_CAPACITY;
            if (newPivot >= size) {
                complexList.add(subcategories.subList(pivot, size));
                break;
            }
            complexList.add(subcategories.subList(pivot, newPivot));
            pivot = newPivot;
        }
        return complexList;
    }

    private SubcategoryDto getSubcategoryDto(List<Subcategory> subcategoryList) {
        return SubcategoryDto.builder()
                .emotions(getEmotions(subcategoryList))
                .score(getAverageScore(subcategoryList))
                .build();
    }

    private List<String> getEmotions(List<Subcategory> subcategoryList) {
        return subcategoryList.stream()
                .map(Subcategory::getDescription)
                .collect(Collectors.toList());
    }

    private double getAverageScore(List<Subcategory> subcategoryList) {
        return (double) Math.round(subcategoryList.stream()
                .mapToInt(Subcategory::getWeight)
                .average()
                .orElse(0)) / PERCENT_COEFFICIENT;
    }

    private String getEmotion(EmotionDto emotionDto) {
        return emotionRepository.findById(emotionDto.getEmotionId())
                .orElseThrow(EMOTION_NOT_FOUND)
                .getDescription();
    }

    @Override
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public AttemptDto calculate(Long quizId, List<EmotionDto> emotions) {
        var attempt = quizAttemptRepository.save(QuizAttempt.builder()
                .createDate(LocalDateTime.now())
                .build());
        var quiz = quizRepository.findById(quizId)
                .orElseThrow(QUIZ_NOT_FOUND);
        getAverageResultValues(emotions).forEach((emotionId, value) -> quizResultRepository.save(QuizResult.builder()
                .quiz(quiz)
                .attempt(attempt)
                .emotion(emotionRepository.findById(emotionId).orElseThrow(EMOTION_NOT_FOUND))
                .score(value.intValue())
                .build()));
        return new AttemptDto(attempt.getId());
    }

    private Map<Long, Double> getAverageResultValues(List<EmotionDto> emotions) {
        return emotions.stream()
                .collect(Collectors.groupingBy(EmotionDto::getEmotionId, LinkedHashMap::new,
                        Collectors.averagingInt(emotionDto -> emotionDto.getValue() * SCORE_COEFFICIENT)));
    }
}
