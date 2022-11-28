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
    }
}
