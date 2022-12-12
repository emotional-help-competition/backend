package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionRequestDto {
    @NotBlank
    private String description;
}
