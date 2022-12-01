package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
import com.epam.emotionalhelp.service.mapper.EmotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;

    @Override
    public Page<EmotionResponseDto> findAll(Pageable pageable) {
        return EmotionMapper.pageEntityToPageDto(emotionRepository.findAll(pageable));
    }

    @Override
    public EmotionResponseDto findById(Long id) {
        return EmotionMapper.toDto(emotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND)));
    }

    @Override
    public EmotionResponseDto addEmotion(EmotionRequestDto emotionRequestDto) {
        return EmotionMapper.toDto(emotionRepository.save(EmotionMapper.toEntity(emotionRequestDto)));
    }


    @Transactional(readOnly = true)
    @Override
    public EmotionResponseDto updateQuestion(Long id, EmotionRequestDto emotionRequestDto) {
        var emotion = emotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        if (emotionRequestDto.getDescription() != null) {
            emotion.setDescription(emotionRequestDto.getDescription());
        }

        return EmotionMapper.toDto(emotionRepository.save(emotion));
    }

    @Transactional(readOnly = true)
    @Override
    public void deleteQuestionById(Long id) {
        var emotion = emotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        emotionRepository.delete(emotion);

    }
}
