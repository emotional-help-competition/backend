package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private EmotionRepository emotionRepository;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionRepository,
                emotionRepository);
    }

    @Test
    void findAll_returnsPageQuestionResponseDto() {
        var questions = generateQuestions();
        var pageable = PageRequest.of(0, 2);
        Page<Question> questionPage = new PageImpl<>(questions, pageable, 5);
        when(questionRepository.findAll(pageable)).thenReturn(questionPage);

        var actualTotalPages = questionService.findAll(pageable).getTotalPages();
        var actualTotalElements = questionService.findAll(pageable).getTotalElements();

        assertEquals(3, actualTotalPages);
        assertEquals(5, actualTotalElements);
        verify(questionRepository, times(2)).findAll(pageable);
        verifyNoMoreInteractions(questionRepository);
    }


    @Test
    void findById_withExistingQuestionId_returnsQuestion() {
        Emotion emotion = new Emotion("test");
        Question question = new Question("test", emotion);
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        var actualQuestion = questionService.findById(1L);
        var actualId = actualQuestion.getId();
        var actualText = actualQuestion.getText();
        var actualEmotion = actualQuestion.getEmotion();

        assertEquals(question.getId(), actualId);
        assertEquals(question.getText(), actualText);
        assertEquals(question.getEmotion(), actualEmotion);

        verify(questionRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void findQuestionByNonexistentId() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> questionService.findById(1L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_withCorrectArguments_returnsCreatedQuestion() {
        Emotion emotion = new Emotion(1L, "test");
        var question = generateQuestion("question", emotion);
        when(questionRepository.save(any())).thenReturn(question);
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("question", emotion);
        var questionResponseDto = questionService.create(questionRequestDto);
        var actualText = question.getText();
        var actualEmotion =    question.getEmotion();

        assertEquals(questionRequestDto.getText(), actualText);
        assertEquals(questionResponseDto.getEmotion(), actualEmotion);
        assertNull(questionResponseDto.getId());

        verify(questionRepository, times(1)).save(any());
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void add_withIncorrectEmotionId_throwsResourceNotFoundException() {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("question", new Emotion(1L, "emotion"));
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> questionService.create(questionRequestDto)).isInstanceOf(ResourceNotFoundException.class);


    }

    @Test
    void updateData_withCorrectArguments_returnsUpdatedQuestion() {
        Question question = new Question(1L, "question", new Emotion(1L, "emotion"));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        Question expected = new Question("question1", new Emotion(2L, "emotion 2"));
        when(questionRepository.save(any())).thenReturn(expected);
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("question1", new Emotion(2L, "emotion2"));
        var actual = questionService.update(1L, questionRequestDto);
        var actualText = expected.getText();
        var actualEmotion = expected.getEmotion();

        assertEquals(actual.getText(),actualText );
        assertEquals(actual.getEmotion(), actualEmotion);

        verify(questionRepository, times(1)).save(any());
        verifyNoMoreInteractions(questionRepository);

    }

    @Test
    void update_withIncorrectQuestionId_throwsResourceNotFoundException() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("question1",
                new Emotion(2L, "emotion2"));
        assertThatThrownBy(() -> questionService.update(1L, questionRequestDto))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_withCorrectQuestionId() {
        Question question = new Question(1L, "question", new Emotion(1L, "emotion"));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        questionService.deleteById(1L);
        verify(questionRepository, times(1)).delete(question);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void deleteById_withIncorrectQuestionId_throwsResourceNotFoundException() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> questionService.deleteById(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    private Question generateQuestion(String text, Emotion emotion) {
        return new Question(text, emotion);
    }

    private Emotion generateEmotion(String description) {
        return new Emotion(description);
    }

    private List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(generateQuestion("question", generateEmotion("test")));
        questions.add(generateQuestion("question1", generateEmotion("test1")));
        questions.add(generateQuestion("question2", generateEmotion("test2")));
        questions.add(generateQuestion("question3", generateEmotion("test3")));
        questions.add(generateQuestion("question4", generateEmotion("test4")));
        return questions;
    }

}