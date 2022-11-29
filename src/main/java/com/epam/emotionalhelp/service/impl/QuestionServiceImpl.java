package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.mapper.QuestionMapper;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> findAll(String text, Pageable pageable) {
       return questionRepository.findAll(text, pageable);
    }

    @Override
    public Question addQuestion(QuestionRequestDto questionRequestDto) {
        return questionRepository.save(QuestionMapper.toEntity(questionRequestDto));
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public Question updateQuestion(QuestionRequestDto questionRequestDto, Long id) {
        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (questionRequestDto.getText() != null) {
            question.setText(questionRequestDto.getText());
        }
        if (questionRequestDto.getEmotion() != null) {
            question.setEmotion(questionRequestDto.getEmotion());
        }

        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(IllegalAccessError::new);
        questionRepository.delete(question);

    }
}


