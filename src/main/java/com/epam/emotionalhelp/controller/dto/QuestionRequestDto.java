package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionRequestDto {
    @NotBlank
    private String text;
    private Emotion emotion;
}
