package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
public class EmotionResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String description;
}
