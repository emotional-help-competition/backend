package com.epam.emotionalhelp.service.mapper;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.model.Quiz;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.stream.Collectors;

@UtilityClass
public class QuizMapper {
    public static Quiz toEntity(QuizRequestDto quizRequestDto) {
        return Quiz.builder()
                .name(quizRequestDto.getName())
                .description(quizRequestDto.getDescription())
                .questions(quizRequestDto.getQuestions())
                .build();
    }

    public static QuizResponseDto toDto(Quiz quiz) {
        return QuizResponseDto.builder()
                .id(quiz.getId())
                .name(quiz.getName())
                .description(quiz.getDescription())
                .createDate(quiz.getCreateDate())
                .questions(quiz.getQuestions())
                .build();
    }

    public static Page<QuizResponseDto> pageEntityToPageDto(Page<Quiz> quiz) {

        var collect = quiz.stream().
                map(QuizMapper::toDto).
                collect(Collectors.toList());
        return new PageImpl<>(collect, quiz.getPageable(), quiz.getTotalElements());
    }
}

