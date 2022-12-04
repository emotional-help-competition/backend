package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class QuizRequestDto {
    String name;
    String description;
    Set<Question> questions;
}
