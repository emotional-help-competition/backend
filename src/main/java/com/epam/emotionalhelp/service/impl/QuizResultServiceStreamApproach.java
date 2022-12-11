package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryContainerDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
import com.epam.emotionalhelp.service.mapper.RecommendationMapper;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizResultServiceStreamApproach {
    private final QuizResultRepository quizResultRepository;
    private final EmotionRepository emotionRepository;
    private final SubcategoryRepository subcategoryRepository;

    public void f(Long attemptId) {
        Map<Emotion, List<SubcategoryDto>> emotionMap = quizResultRepository.findAllByAttemptId(attemptId).stream()
                .map(RecommendationMapper::toEmotionDto)
                .collect(Collectors.toMap(this::getEmotion,
                        this::getListsForEmotion));

        for (Map.Entry<Emotion, List<SubcategoryDto>> entry : emotionMap.entrySet()) {
            Emotion k = entry.getKey();
            List<SubcategoryDto> v = entry.getValue();
            System.out.println(k);
            v.forEach(x -> System.out.println("\t" + x));
        }
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

        int hexagonsNumber = 4;
        int hexagonCapacity = 3;

        List<List<Subcategory>> complexList = new ArrayList<>();

        final int size = subcategories.size();
        int pivot = 0;

        for (int i = 0; i < hexagonsNumber; i++) {
            int newPivot = pivot + hexagonCapacity;
            if (newPivot > size) {
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
                .emotions(subcategoryList.stream()
                        .map(Subcategory::getDescription)
                        .map(EmotionRequestDto::new)
                        .collect(Collectors.toList()))
                .score(subcategoryList.stream()
                        .mapToInt(Subcategory::getWeight)
                        .average()
                        .orElse(0))
                .build();
    }

    private Emotion getEmotion(EmotionDto emotionDto) {
        return emotionRepository.findById(emotionDto.getEmotionId())
                .get();
    }

    @Bean
    CommandLineRunner commandLineRunner(QuizResultServiceStreamApproach serv) {
        return x -> {
            serv.f(1L);
        };
    }
}
