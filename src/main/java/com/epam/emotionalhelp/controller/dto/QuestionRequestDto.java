package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class QuestionRequestDto {
    @NotBlank
    String text;
    Emotion emotion;
}
