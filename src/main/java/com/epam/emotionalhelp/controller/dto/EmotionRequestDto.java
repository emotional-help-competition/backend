package com.epam.emotionalhelp.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmotionRequestDto {
    @NotBlank
    private String description;
}
