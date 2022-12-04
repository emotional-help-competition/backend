package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUESTIONS;


@RestController
@RequestMapping(path = QUESTIONS)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public Page<QuestionResponseDto> findAll(Pageable pageable) {
        return questionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public QuestionResponseDto findById(@Min(1) @PathVariable Long id) {
        return questionService.findById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuestionResponseDto create(@RequestBody QuestionRequestDto questionRequestDto) {
        return questionService.create(questionRequestDto);
    }

    @PatchMapping("/{id}")
    public QuestionResponseDto update(@Min(1) @PathVariable Long id,
                                      @RequestBody QuestionRequestDto questionRequestDto) {
        return questionService.update(id, questionRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@Min(1) @PathVariable Long id) {
        questionService.deleteById(id);
    }
}
