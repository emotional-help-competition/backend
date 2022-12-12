package com.epam.emotionalhelp.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EmotionRequestDto {
    @NotBlank
    private String description;
}
