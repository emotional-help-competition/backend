package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class EmotionResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String description;
}
