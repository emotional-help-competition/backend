package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String text;
    private Emotion emotion;
}
