package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.EmotionCategory;
import com.epam.emotionalhelp.model.Quiz;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizResultServiceImpl implements QuizResultService {
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
    public int calculate(Long quizId, List<EmotionDto> emotions) {
        //Find passed Quiz
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        //Create QuizAttempt object
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setCreateDate(LocalDateTime.now());
        QuizAttempt quizAttemptFinal = quizAttemptRepository.save(quizAttempt);
        //Filter the list to divide data by Emotions
        Map<EmotionCategory, List<EmotionDto>> filteredList = filterListByEmotions(emotions);
        Map<Emotion, Integer> emotionPercentages = calculatePercentagesByEmotion(filteredList);
        //Get the set of emotions
        for (Map.Entry<Emotion, Integer> entry : emotionPercentages.entrySet()) {
            //Create QuizResult object
            QuizResult quizResult = new QuizResult();
            quiz.ifPresent(quizResult::setQuiz);
            quizResult.setAttempt(quizAttemptFinal);
            quizResult.setEmotion(entry.getKey());
            quizResult.setScore(entry.getValue());
            quizResultRepository.save(quizResult);
        }
        return Math.toIntExact(quizAttempt.getId());
    }

    @Override
    public Map<Emotion, List<Subcategory>> findQuizResultByAttemptId(Long id) {
        //Find QuizResult
        List<QuizResult> list = quizResultRepository.findQuizResultsByAttemptId(id);
        Map<Emotion, List<Subcategory>> map = new HashMap<>();
        //Extract Emotion and get Subcategories based on '%'
        for (QuizResult quizResult : list) {
            Emotion emotion = quizResult.getEmotion();
            List<Subcategory> subcategories = subcategoryRepository.findAllSubcategories(quizResult.getScore(), emotion.getId());
            map.put(emotion, subcategories);
        }
        return map;
    }

    private Map<EmotionCategory, List<EmotionDto>> filterListByEmotions(List<EmotionDto> emotions) {
        Map<EmotionCategory, List<EmotionDto>> map = new HashMap<>();
        for (EmotionDto emotionDto : emotions) {
            //Find emotion
            Optional<Emotion> optionalEmotion = emotionRepository.findById(emotionDto.getEmotionId());
            Emotion emotion = new Emotion();
            if (optionalEmotion.isPresent()) {
                emotion = optionalEmotion.get();
            }
            //Extract description of found emotion
            String description = emotion.getDescription().toUpperCase();
            //Check if map already contains EMOTION
            if (map.containsKey(EmotionCategory.valueOf(description))) {
                map.get(EmotionCategory.valueOf(description)).add(emotionDto);
            } else {
                List<EmotionDto> list = new ArrayList<>();
                map.put(EmotionCategory.valueOf(description), list);
            }
        }
        return map;
    }

    private Map<Emotion, Integer> calculatePercentagesByEmotion(Map<EmotionCategory, List<EmotionDto>> filteredList) {
        Map<Emotion, Integer> map = new HashMap<>();
        for (Map.Entry<EmotionCategory, List<EmotionDto>> entry : filteredList.entrySet()) {
            Emotion emotion = emotionRepository.findEmotionByDescription(entry.getKey().getName());
            map.put(emotion, calculateListPercentages(entry.getValue()));
        }
        return map;
    }

    private int calculateListPercentages(List<EmotionDto> list) {
        int sum = 0;
        for (EmotionDto emotionDto : list) {
            sum += emotionDto.getValue();
        }
        return sum / list.size();
    }
}