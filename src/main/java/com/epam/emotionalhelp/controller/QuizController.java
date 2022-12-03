package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ;

@RestController
@RequestMapping(path = QUIZ)
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @GetMapping
    public Page<QuizResponseDto> findAll(Pageable pageable) {
        return quizService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public QuizResponseDto findById(@PathVariable Long id) {
        return quizService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuizResponseDto create(@RequestBody QuizRequestDto quizRequestDto) {
        return quizService.create(quizRequestDto);
    }

    @PatchMapping("/{id}")
    public QuizResponseDto update(@PathVariable Long id, @RequestBody QuizRequestDto quizRequestDto) {
        return quizService.update(id, quizRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quizService.deleteById(id);
    }
}
