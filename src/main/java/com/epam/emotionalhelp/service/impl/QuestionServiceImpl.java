package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Collection<Question> findAll() {
        return questionRepository.findAll();
//        return List.of(Question.builder().id(1L).text("are you angry?").emotionId(112L).build(),
//                Question.builder().id(2L).text("are you sad?").emotionId(11L).build(),
//                Question.builder().id(3L).text("are you happy?").emotionId(112L).build());
    }
}
