package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class EmotionResponseDto {
    private Long id;
    private String description;
}
