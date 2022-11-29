package com.epam.emotionalhelp.mapper;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.model.Emotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class EmotionMapper {
    public static Emotion toEntity(EmotionRequestDto emotionRequestDto){
        return Emotion.builder()
                .description(emotionRequestDto.getDescription())
                .build();

    }
    public static EmotionResponseDto toDto(Emotion emotion){
        return EmotionResponseDto.builder()
                .id(emotion.getId())
                .description(emotion.getDescription())
                .build();
    }
    public static Page<EmotionResponseDto> pageEntityToPageDto(Page<Emotion> emotions) {

        List<EmotionResponseDto>collect = emotions.stream().
                map(EmotionMapper::toDto).
                collect(Collectors.toList());
        return new PageImpl<>(collect, emotions.getPageable(), emotions.getTotalElements());
    }
}
