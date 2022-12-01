package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class EmotionResponseDto {
    Long id;
    String description;
}
