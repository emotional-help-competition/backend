package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.model.QuizAttempt;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.repository.QuizAttemptRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizResultServiceImpl implements QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final QuizRepository quizRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public void calculateResult(Long quizId, List<EmotionDto> emotions) {
        //Create QuizResult object
        QuizResult quizResult = new QuizResult();
        //Set an id from the path variable
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        quiz.ifPresent(quizResult::setQuiz);
        //Create QuizAttempt object
        QuizAttempt quizAttempt = new QuizAttempt();
        quizAttempt.setCreateDate(LocalDateTime.now());
        QuizAttempt quizAttemptFinal = quizAttemptRepository.save(quizAttempt);
        quizResult.setAttempt(quizAttemptFinal);
        //
        for (EmotionDto emotionDto : emotions) {

        }


        //Stream<EmotionDto> stream = emotions.stream().map();

        quizResultRepository.save(quizResult);

    }
}