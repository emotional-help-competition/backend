package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class EmotionRequestDto {
     String description;
}
