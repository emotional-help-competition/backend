package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Data;

@Data
public class QuestionRequestDto {
    private String text;
    private Emotion emotion;
}
