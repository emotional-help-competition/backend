package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class QuestionResponseDto {
    @NotNull
    Long id;
    @NotBlank
    String text;
    Emotion emotion;
}
