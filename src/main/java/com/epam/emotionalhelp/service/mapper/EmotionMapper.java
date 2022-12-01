package com.epam.emotionalhelp.service.mapper;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.model.Emotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

public class EmotionMapper {
    public static Emotion toEntity(EmotionRequestDto emotionRequestDto){
        return new Emotion(emotionRequestDto.getDescription());

    }
    public static EmotionResponseDto toDto(Emotion emotion){
        return new EmotionResponseDto(emotion.getId(), emotion.getDescription());
    }
    public static Page<EmotionResponseDto> pageEntityToPageDto(Page<Emotion> emotions) {

        var collect = emotions.stream().
                map(EmotionMapper::toDto).
                collect(Collectors.toList());
        return new PageImpl<>(collect, emotions.getPageable(), emotions.getTotalElements());
    }
}
