package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor

@Builder
public class QuestionRequestDto {
    String text;
    Emotion emotion;
}
