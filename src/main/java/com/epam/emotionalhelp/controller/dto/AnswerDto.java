package com.epam.emotionalhelp.controller.dto;

import lombok.Value;

@Value
public class AnswerDto {
    Long emotionId;
    Integer score;
}
