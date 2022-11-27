package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizResultServiceImpl implements QuizResultService {
    @Override
    public Map<Long, Integer> calculateResult(List<EmotionDto> emotions) {
        return emotions.stream()
                .collect(Collectors.groupingBy(EmotionDto::getEmotionId, LinkedHashMap::new,
                        Collectors.summingInt(EmotionDto::getValue)));
    }
}
