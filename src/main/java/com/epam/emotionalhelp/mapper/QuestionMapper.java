package com.epam.emotionalhelp.mapper;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {
    public static Question toEntity(QuestionRequestDto questionRequestDto){
        return Question.builder()
                .text(questionRequestDto.getText())
                .emotion(questionRequestDto.getEmotion())
                .build();

    }
    public static QuestionResponseDto toDto(Question question){
        return QuestionResponseDto.builder()
                .id(question.getId())
                .text(question.getText())
                .emotion(question.getEmotion())
                .build();
    }
    public static Page<QuestionResponseDto> pageEntityToPageDto(Page<Question> questions) {

        List<QuestionResponseDto> collect = questions.stream().
                map(QuestionMapper::toDto).
                collect(Collectors.toList());
        return new PageImpl<>(collect, questions.getPageable(), questions.getTotalElements());
    }

}
