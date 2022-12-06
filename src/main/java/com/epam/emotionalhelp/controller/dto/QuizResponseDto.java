package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Builder
public class QuizResponseDto {
    private Long id;
    private String name;
    private String description;
    private ZonedDateTime createDate;
    private Set<Question> questions;
}
