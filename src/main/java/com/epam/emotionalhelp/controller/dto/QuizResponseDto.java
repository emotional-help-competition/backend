package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Set;

@Value
@Builder
public class QuizResponseDto {
    Long id;
    String name;
    String description;
    ZonedDateTime createDate;
    Set<Question> questions;
}
