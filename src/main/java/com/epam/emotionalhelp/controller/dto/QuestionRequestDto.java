package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Emotion;
import lombok.Value;

@Value
public class QuestionRequestDto {
  String text;
  Emotion emotion;
}
