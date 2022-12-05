package com.epam.emotionalhelp.controller.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class EmotionRequestDto {
    @NotBlank
    String description;
}
