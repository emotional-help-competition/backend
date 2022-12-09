package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class QuizResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime createDate;
    private final Set<Question> questions;
}
