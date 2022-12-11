package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryDto;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
import com.epam.emotionalhelp.service.mapper.RecommendationMapper;
import com.epam.emotionalhelp.service.util.ExceptionSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizResultServiceStreamApproach {
    private static final int HEXAGONS_QUANTITY = 4;
    private static final int HEXAGON_CAPACITY = 3;
    private static final int PERCENT_COEFFICIENT = 100;

    private final QuizResultRepository quizResultRepository;
    private final EmotionRepository emotionRepository;
    private final SubcategoryRepository subcategoryRepository;

    public List<EmotionalMapDto> getEmotionMap(Long attemptId) {
        return quizResultRepository.findAllByAttemptId(attemptId).stream()
                .map(RecommendationMapper::toEmotionDto)
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
     * Example:
     * 4 hexagon * 3 category;
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

    private List<EmotionRequestDto> getEmotions(List<Subcategory> subcategoryList) {
        return subcategoryList.stream()
                .map(Subcategory::getDescription)
                .map(EmotionRequestDto::new)
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
                .orElseThrow(ExceptionSupplier.EMOTION_NOT_FOUND)
                .getDescription();
    }

    @Bean
    CommandLineRunner commandLineRunner(QuizResultServiceStreamApproach serv) {
        return x -> {
            var emotionMap = serv.getEmotionMap(1L);

            emotionMap.forEach(System.out::println);
        };
    }
}
