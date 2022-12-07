package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
import com.epam.emotionalhelp.service.mapper.EmotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    @Autowired
    private final EmotionRepository emotionRepository;

    @Override
    public Page<EmotionResponseDto> findAll(Pageable pageable) {
        return EmotionMapper.pageEntityToPageDto(emotionRepository.findAll(pageable));
    }

    @Override
    public EmotionResponseDto findById(Long id) {
        return EmotionMapper.toDto(findEmotionById(id));
    }

    @Override
    public EmotionResponseDto create(EmotionRequestDto emotionRequestDto) {
        return EmotionMapper.toDto(emotionRepository.save(EmotionMapper.toEntity(emotionRequestDto)));
    }


    @Transactional
    @Override
    public EmotionResponseDto update(Long id, EmotionRequestDto emotionRequestDto) {
        var emotion = findEmotionById(id);
        if (emotionRequestDto.getDescription() != null) {
            emotion.setDescription(emotionRequestDto.getDescription());
        }

        return EmotionMapper.toDto(emotionRepository.save(emotion));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var emotion = findEmotionById(id);
        emotionRepository.delete(emotion);
    }

    private Emotion findEmotionById(Long id) {
        return emotionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
    }
}
