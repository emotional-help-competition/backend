package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Set;

@Value
@AllArgsConstructor
@Builder
public class QuizResponseDto {
    Long id;
    String name;
    String description;
    ZonedDateTime createDate;
    Set<Question> questions;
}
