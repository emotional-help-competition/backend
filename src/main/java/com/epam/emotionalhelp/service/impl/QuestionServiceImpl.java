package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.service.mapper.QuestionMapper;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> findAll(Pageable pageable) {
       return questionRepository.findAll(pageable);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));

    }
    @Override
    public Question addQuestion(QuestionRequestDto questionRequestDto) {
        return questionRepository.save(QuestionMapper.toEntity(questionRequestDto));
    }


    @Transactional(readOnly = true)
    @Override
    public Question updateQuestion(Long id, QuestionRequestDto questionRequestDto) {
        var question = questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        if (questionRequestDto.getText() != null) {
            question.setText(questionRequestDto.getText());
        }
        if (questionRequestDto.getEmotion() != null) {
            question.setEmotion(questionRequestDto.getEmotion());
        }

        return questionRepository.save(question);
    }
    @Transactional(readOnly = true)
    @Override
    public void deleteQuestionById(Long id) {
        var question = questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        questionRepository.delete(question);

    }
}


