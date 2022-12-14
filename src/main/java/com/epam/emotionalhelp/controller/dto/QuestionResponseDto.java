package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class QuestionResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String text;
    private Emotion emotion;
}
