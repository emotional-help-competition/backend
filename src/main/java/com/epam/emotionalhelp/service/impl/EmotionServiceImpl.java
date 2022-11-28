package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.module.Emotion;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository emotionRepository;

    @Override
    public Collection<Emotion> findAll() {
        return (Collection<Emotion>) emotionRepository.findAll();
    }
}
