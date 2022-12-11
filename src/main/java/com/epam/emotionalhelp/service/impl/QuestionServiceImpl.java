package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
import com.epam.emotionalhelp.service.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.epam.emotionalhelp.service.util.ExceptionSupplier.*;

/**
 * The type Question service.
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final EmotionRepository emotionRepository;

    @Override
    public Page<QuestionResponseDto> findAll(Pageable pageable) {
       return QuestionMapper.pageEntityToPageDto(questionRepository.findAll(pageable));
    }

    @Override
    public QuestionResponseDto findById(Long id) {
        return QuestionMapper.toDto(findQuestionById(id));
    }


    @Override
    public QuestionResponseDto create(QuestionRequestDto questionRequestDto) {
        var emotion = emotionRepository.findById(questionRequestDto.getEmotion().getId())
                .orElseThrow(EMOTION_NOT_FOUND);
        var question = QuestionMapper.toEntity(questionRequestDto);
        question.setEmotion(emotion);
        return QuestionMapper.toDto(questionRepository.save(question));
    }

    @Transactional
    @Override
    public QuestionResponseDto update(Long id, QuestionRequestDto questionRequestDto) {
        var question = findQuestionById(id);
        if (questionRequestDto.getText() != null) {
            question.setText(questionRequestDto.getText());
        }
        if (questionRequestDto.getEmotion() != null) {
            question.setEmotion(questionRequestDto.getEmotion());
        }

        return QuestionMapper.toDto(questionRepository.save(question));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var question = findQuestionById(id);
        questionRepository.delete(question);
    }

    private Question findQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(QUESTION_NOT_FOUND);
    }
}


