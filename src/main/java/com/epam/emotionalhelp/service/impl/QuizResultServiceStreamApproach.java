package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryContainerDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
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
        List<EmotionDto> emotions = quizResultRepository.findAllByAttemptId(attemptId).stream()
                .map(RecommendationServiceImpl.Mapper::toEmotionDto)
                .collect(Collectors.toList());

        Map<Emotion, List<SubcategoryDto>> emotionMap = emotions.stream()
                .collect(Collectors.toMap(this::getEmotion,
                        this::getListsForEmotion));

        emotionMap.forEach((k, v) -> System.out.println(k + ">" + v));

    }

    private List<SubcategoryDto> getListsForEmotion(EmotionDto emotion) {
        List<Subcategory> subcategories = getSubcategoriesForEmotion(emotion);
        final int quantity = getHexagonsQuantity(subcategories.size());
        Collection<List<Subcategory>> hexagonList = subcategories.stream()
                .collect(Collectors.groupingBy(emotionDto -> new Random().nextInt(quantity)))
                .values();

        return hexagonList.stream()
                .map(subcategoryList -> {

                    ArrayList<EmotionRequestDto> emotionRequestDtos = new ArrayList<>();

                    for (Subcategory sub : subcategoryList) {
                        EmotionRequestDto emotionRequestDto = new EmotionRequestDto();
                        emotionRequestDto.setDescription(sub.getDescription());
                        emotionRequestDtos.add(emotionRequestDto);
                    }

                    Double score = subcategoryList.stream()
                            .mapToInt(Subcategory::getWeight)
                            .average()
                            .orElse(0);

                    return SubcategoryDto.builder()
                            .emotions(emotionRequestDtos)
                            .score(score)
                            .build();
                })
                .collect(Collectors.toList());

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class SubcategoryDto {
        private Collection<EmotionRequestDto> emotions;
        private Double score;
    }

    /**
     * We expect a list of subcategory with size <= 12;
     * 4 hexagon * 3 category;
     * 0 cat - 0 hex;
     * 1..3 cat - 1 hex;
     * 4..6 cat - 2 hex;
     * 7..9 cat - 3 hex;
     * 10..12 cat - 4 hex;
     */
    private int getHexagonsQuantity(int arraySize) {
        return (int) Math.ceil((double) arraySize / 3);
    }

    private Emotion getEmotion(EmotionDto emotionDto) {
        return emotionRepository.findById(emotionDto.getEmotionId())
                .get();
    }

    private List<Subcategory> getSubcategoriesForEmotion(EmotionDto emotionDto) {
        return subcategoryRepository.findAll().stream()
                .filter(subcategory -> subcategory.getEmotion().getId().equals(emotionDto.getEmotionId()))
                .filter(subcategory -> subcategory.getWeight() <= emotionDto.getValue())
                .collect(Collectors.toList());
    }

    @Bean
    CommandLineRunner commandLineRunner(QuizResultServiceStreamApproach serv) {
        return x -> {
            serv.f(1L);
        };
    }

}
