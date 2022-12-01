package com.epam.emotionalhelp.service.mapper;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.model.Question;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.stream.Collectors;

@UtilityClass
public class QuestionMapper {

    public static Question toEntity(QuestionRequestDto questionRequestDto) {
        return new Question(questionRequestDto.getText(), questionRequestDto.getEmotion());
    }

    public static QuestionResponseDto toDto(Question question) {
        return new QuestionResponseDto(question.getId(), question.getText(), question.getEmotion());
    }

    public static Page<QuestionResponseDto> pageEntityToPageDto(Page<Question> questions) {

        var collect = questions.stream()
                .map(QuestionMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, questions.getPageable(), questions.getTotalElements());
    }
}
