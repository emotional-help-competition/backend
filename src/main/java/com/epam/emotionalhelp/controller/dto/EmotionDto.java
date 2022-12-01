package com.epam.emotionalhelp.controller.dto;

import lombok.Data;
import lombok.Value;

@Value
public class EmotionDto {
    Long emotionId;
    Integer value;
}
