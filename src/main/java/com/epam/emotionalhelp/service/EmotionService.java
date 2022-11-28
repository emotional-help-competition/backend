package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.module.Emotion;

import java.util.Collection;

public interface EmotionService {
    Collection<Emotion> findAll();
}
