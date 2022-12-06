package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Data;

@Data
public class QuestionResponseDto {
    private Long id;
    private String text;
    private Emotion emotion;
}
