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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
    public List<List<Subcategory>> calculateResult(Long quizId, List<EmotionDto> emotions) {
        //Create QuizResult object
        QuizResult quizResult = new QuizResult();
        //Set an quizId from the path variable
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(quizResult::setQuiz);
        //Create QuizAttempt object
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setCreateDate(LocalDateTime.now());
        QuizAttempt quizAttemptFinal = quizAttemptRepository.save(quizAttempt);
        quizResult.setAttempt(quizAttemptFinal);
        //Calculating the data for producing subcategories
        List<List<EmotionDto>> filteredList = filterListByEmotions(emotions);
        List<Integer> percentages = calculatePercentagesByEmotions(filteredList);
        List<List<Subcategory>> subcategories = fillSubcategories(percentages);
        //Get the set of emotions
        Set<Emotion> emotionSet = findListOfEmotions(emotions);
        quizResult.setEmotions(emotionSet);
        //Calculate QuizResult score
        int score = calculateQuizResultScore(emotions);
        quizResult.setScore(score);
        //Create Quiz Result
        quizResultRepository.save(quizResult);

        return subcategories;
    }

    private List<List<EmotionDto>> filterListByEmotions(List<EmotionDto> emotions) {

        List<List<EmotionDto>> filteredList = new ArrayList<>();
        List<EmotionDto> angerList = new ArrayList<>();
        List<EmotionDto> fearList = new ArrayList<>();
        List<EmotionDto> sadnessList = new ArrayList<>();
        List<EmotionDto> loveList = new ArrayList<>();
        List<EmotionDto> surpriseList = new ArrayList<>();
        List<EmotionDto> joyList = new ArrayList<>();
        List<EmotionDto> acceptedList = new ArrayList<>();
        List<EmotionDto> afraidList = new ArrayList<>();
        List<EmotionDto> scaredList = new ArrayList<>();

        for (EmotionDto emotionDto : emotions) {
            //Find emotion by ID
            Optional<Emotion> optionalEmotion = emotionRepository.findById(emotionDto.getEmotionId());
            Emotion emotion = new Emotion();
            if (optionalEmotion.isPresent()) {
                emotion = optionalEmotion.get();
            }
            if (Objects.equals(emotion.getDescription(), EmotionCategory.ANGER.getName())) {
                angerList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.FEAR.getName())) {
                fearList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.SADNESS.getName())) {
                sadnessList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.LOVE.getName())) {
                loveList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.SURPRISE.getName())) {
                surpriseList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.JOY.getName())) {
                joyList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.ACCEPTED.getName())) {
                acceptedList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.AFRAID.getName())) {
                afraidList.add(emotionDto);
            } else if (Objects.equals(emotion.getDescription(), EmotionCategory.SCARED.getName())) {
                scaredList.add(emotionDto);
            }
        }

        filteredList.add(angerList);
        filteredList.add(fearList);
        filteredList.add(sadnessList);
        filteredList.add(loveList);
        filteredList.add(surpriseList);
        filteredList.add(joyList);
        filteredList.add(acceptedList);
        filteredList.add(afraidList);
        filteredList.add(scaredList);

        return filteredList;
    }

    private Set<Emotion> findListOfEmotions(List<EmotionDto> emotions) {
        Set<Emotion> emotionSet = new HashSet<>();
        for (EmotionDto emotionDto : emotions) {
            Optional<Emotion> optionalEmotion = emotionRepository.findById(emotionDto.getEmotionId());
            Emotion emotion = new Emotion();
            if (optionalEmotion.isPresent()) {
                emotion = optionalEmotion.get();
            }
            emotionSet.add(emotion);
        }
        return emotionSet;
    }

    private List<Integer> calculatePercentagesByEmotions(List<List<EmotionDto>> filteredList) {
        List<Integer> percentagesList = new ArrayList<>();
        for (List<EmotionDto> list : filteredList) {
            int listSum = 0;
            int listLength = list.size();
            for (EmotionDto emotionDto : list) {
                listSum += emotionDto.getValue();
            }
            percentagesList.add(listSum / listLength);
        }
        return percentagesList;
    }

    private int calculateQuizResultScore(List<EmotionDto> emotions) {
        int sum = 0;
        for (EmotionDto emotion : emotions) {
            sum += emotion.getValue();
        }
        return sum / emotions.size();
    }

    private List<List<Subcategory>> fillSubcategories(List<Integer> list) {
        List<List<Subcategory>> subcategory = new ArrayList<>();
        List<Subcategory> angerList = subcategoryRepository.findAllSubcategories(list.get(0), 1L);
        List<Subcategory> fearList = subcategoryRepository.findAllSubcategories(list.get(1), 2L);
        List<Subcategory> sadnessList = subcategoryRepository.findAllSubcategories(list.get(2), 3L);
        List<Subcategory> loveList = subcategoryRepository.findAllSubcategories(list.get(3), 4L);
        List<Subcategory> surpriseList = subcategoryRepository.findAllSubcategories(list.get(4), 5L);
        List<Subcategory> joyList = subcategoryRepository.findAllSubcategories(list.get(5), 6L);
        List<Subcategory> acceptedList = subcategoryRepository.findAllSubcategories(list.get(6), 7L);
        List<Subcategory> afraidList = subcategoryRepository.findAllSubcategories(list.get(7), 8L);
        List<Subcategory> scaredList = subcategoryRepository.findAllSubcategories(list.get(8), 9L);
        subcategory.add(angerList);
        subcategory.add(fearList);
        subcategory.add(sadnessList);
        subcategory.add(loveList);
        subcategory.add(surpriseList);
        subcategory.add(joyList);
        subcategory.add(acceptedList);
        subcategory.add(afraidList);
        subcategory.add(scaredList);
        return subcategory;
    }
}