package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.mapper.EmotionMapper;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;

    @Override
    public Page<Emotion> findAll(Pageable pageable) {
        return emotionRepository.findAll(pageable);
    }

    @Override
    public Emotion addQuestion(EmotionRequestDto emotionRequestDto) {
        return emotionRepository.save(EmotionMapper.toEntity(emotionRequestDto));
    }

    @Override
    public Emotion findById(Long id) {
        return emotionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Emotion updateQuestion(EmotionRequestDto emotionRequestDto, Long id) {
        Emotion emotion = emotionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (emotionRequestDto.getDescription() != null) {
            emotion.setDescription(emotionRequestDto.getDescription());
        }

        return emotionRepository.save(emotion);
    }

    @Override
    public void deleteQuestionById(Long id) {
        Emotion emotion = emotionRepository.findById(id).orElseThrow(IllegalAccessError::new);
        emotionRepository.delete(emotion);

    }
}
