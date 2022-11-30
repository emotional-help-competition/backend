package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.response.ResponseHandler;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.mapper.QuestionMapper;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static com.epam.emotionalhelp.controller.util.EndpointName.QUESTIONS;

@RestController
@RequestMapping(path = QUESTIONS)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Object> addQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        Question question = questionService.addQuestion(questionRequestDto);
        return ResponseHandler.
                generateResponse(ResponseMessage.SUCCESSFULLY_CREATED,
                        HttpStatus.CREATED,
                        QuestionMapper.toDto(question));
    }

    @GetMapping
    public ResponseEntity<Object> findAllQuestions(@RequestParam(required = false) String text, Pageable pageable) {
        Page<Question> all = questionService.findAll(text, pageable);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_RECEIVED, HttpStatus.OK,
                QuestionMapper.pageEntityToPageDto(all));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionById(@PathVariable(value = "id") Long id) {
        Question questionById = questionService.findById(id);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_RECEIVED, HttpStatus.OK,
                QuestionMapper.toDto(questionById));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateQuestion(@RequestBody QuestionRequestDto questionRequestDto,
                                                 @PathVariable(value = "id") Long id) {
        Question updateQuestion = questionService.updateQuestion(questionRequestDto, id);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_UPDATED,
                HttpStatus.OK, QuestionMapper.toDto(updateQuestion));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuestionById(@PathVariable(value = "id") Long id) {
        questionService.deleteQuestionById(id);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_DELETED, HttpStatus.NO_CONTENT);
    }

}
