package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.AttemptDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryContainerDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.EmotionCategory;
import com.epam.emotionalhelp.model.QuizAttempt;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizAttemptRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizResultServiceImpl implements QuizResultService {
    private static final int MAX_SCORE_VALUE = 5;
    private static final int PERCENTAGE_VALUE = 100;
    private static final int SUBCATEGORIES_LIMIT_VALUE = 12;
    private static final int MAX_CONTAINER_SIZE = 3;

    private static final int LIST_SLICE_SIZE = 6;

    private final QuizResultRepository quizResultRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final EmotionRepository emotionRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultRepository.findAll(pageable);
    }

    @Override
    public AttemptDto calculate(Long quizId, List<EmotionDto> emotions) {
        //Find passed Quiz
        var quiz = quizRepository.findById(quizId);
        //Create QuizAttempt object
        var quizAttempt = new QuizAttempt();
        quizAttempt.setCreateDate(LocalDateTime.now());
        var quizAttemptFinal = quizAttemptRepository.save(quizAttempt);
        //Filter the list to divide data by Emotions
        var filteredList = filterListByEmotions(emotions);
        var emotionPercentages = calculatePercentagesByEmotion(filteredList);
        //Get the set of emotions
        for (Map.Entry<Emotion, Integer> entry : emotionPercentages.entrySet()) {
            //Create QuizResult object
            var quizResult = new QuizResult();
            quiz.ifPresent(quizResult::setQuiz);
            quizResult.setAttempt(quizAttemptFinal);
            quizResult.setEmotion(entry.getKey());
            quizResult.setScore(entry.getValue());
            quizResultRepository.save(quizResult);
        }
        return new AttemptDto(quizAttempt.getId());
    }

    @Override
    public List<EmotionalMapDto> findQuizResultsByAttemptId(Long attemptId) {
        //Find QuizResult(Emotion , Overall score)
        var list = quizResultRepository.findAllByAttemptId(attemptId);

        //Create list to return
        var resultList = new ArrayList<EmotionalMapDto>();
        //Extract Emotion and get Subcategories based on '%'
        for (QuizResult quizResult : list) {
            var emotion = quizResult.getEmotion();
            //Create QuizResultEmotionDto
            var quizResultEmotionDto = new EmotionalMapDto();
            quizResultEmotionDto.setCategory(emotion.getDescription());
            //Find All Subcategories
            //If score is equal to 0 it means that list with subcategories should be empty
            if (quizResult.getScore() == 0) {
                quizResultEmotionDto.setSubCategories(new ArrayList<>());
            } else {
                int percentage = (int) (((double) quizResult.getScore() / MAX_SCORE_VALUE) * PERCENTAGE_VALUE);
                var allSubcategories = subcategoryRepository.findAllSubcategories(percentage, emotion.getId());
                var subcategories = allSubcategories
                        .stream()
                        .limit(SUBCATEGORIES_LIMIT_VALUE)
                        .sorted(Comparator.comparing(Subcategory::getWeight))
                        .collect(Collectors.toList());
                var subcategoryList = ListUtils.partition(subcategories, MAX_CONTAINER_SIZE);
                var categoryList = new ArrayList<SubcategoryContainerDto>();
                for (List<Subcategory> partition : subcategoryList) {
                    var category = partition.stream().map(Subcategory::getDescription).collect(Collectors.toSet());
                    double score = (double) partition.stream().mapToInt(Subcategory::getWeight).max().orElse(0) / PERCENTAGE_VALUE;
                    categoryList.add(new SubcategoryContainerDto(category, score));
                }
                quizResultEmotionDto.setSubCategories(categoryList);
            }

            //Add QuizResultEmotionDto to the List
            resultList.add(quizResultEmotionDto);
        }
        return resultList;
    }

    private Map<EmotionCategory, List<EmotionDto>> filterListByEmotions(List<EmotionDto> inputEmotions) {
        Map<EmotionCategory, List<EmotionDto>> map = new HashMap<>();
        List<EmotionDto> emotions;
        if (inputEmotions.size() > LIST_SLICE_SIZE) {
            emotions = new ArrayList<>(inputEmotions.subList(0, LIST_SLICE_SIZE));
        } else {
            emotions = new ArrayList<>(inputEmotions);
        }
        //Fill map with EMOTIONS and its SUBCATEGORIES
        for (EmotionDto emotionDto : emotions) {
            //Find emotion
            var emotion = emotionRepository.findById(emotionDto.getEmotionId()).orElse(new Emotion());
            //Extract description of found emotion
            String description = emotion.getDescription().toUpperCase();
            //Check if map already contains EMOTION
            if (map.containsKey(EmotionCategory.valueOf(description))) {
                map.get(EmotionCategory.valueOf(description)).add(emotionDto);
            } else {
                var list = new ArrayList<EmotionDto>();
                list.add(emotionDto);
                map.put(EmotionCategory.valueOf(description), list);
            }
        }
        //Fill map with EMOTIONS which are not included in the input list
        int n = 6 - map.values().size();
        for (int i = 0; i < n; i++) {
            for (EmotionCategory emotionCategory : EmotionCategory.values()) {
                if (!map.containsKey(emotionCategory)) {
                    map.put(emotionCategory, new ArrayList<>());
                    break;
                }
            }
        }
        return map;
    }

    private Map<Emotion, Integer> calculatePercentagesByEmotion
            (Map<EmotionCategory, List<EmotionDto>> filteredList) {
        Map<Emotion, Integer> map = new HashMap<>();
        filteredList.forEach((key, value) -> {
            var emotion = emotionRepository.findEmotionByDescription(key.getName());
            map.put(emotion, calculateListPercentages(value));
        });
        return map;
    }

    private int calculateListPercentages(List<EmotionDto> list) {
        //If there weren't questions for particular EMOTION size of the list will be 0
        if (list.size() == 0) {
            return 0;
        }
        int sum = list.stream().mapToInt(EmotionDto::getValue).sum();
        return sum / list.size();
    }
}