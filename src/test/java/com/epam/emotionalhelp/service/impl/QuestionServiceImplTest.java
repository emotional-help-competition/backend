package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.service.QuestionService;
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
    private QuestionService  questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionRepository,
                emotionRepository);
    }

    @Test
    void findAll_returnsPageQuestionResponseDto() {
        List<Question> questions = generateQuestions();
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Question> questionPage = new PageImpl<>(questions, pageable, 5);
        when(questionRepository.findAll(pageable)).thenReturn(questionPage);
        assertEquals(3, questionService.findAll(pageable).getTotalPages());
        assertEquals(5, questionService.findAll(pageable).getTotalElements());
        verify(questionRepository, times(2)).findAll(pageable);
        verifyNoMoreInteractions(questionRepository);
    }


    @Test
    public void findById_withExistingQuestionId_returnsQuestion() {
        Emotion emotion = new Emotion("test");
        Question question = new Question("test", emotion);
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        QuestionResponseDto actualQuestion = questionService.findById(1L);
        assertEquals(question.getId(), actualQuestion.getId());
        assertEquals(question.getText(), actualQuestion.getText());
        assertEquals(question.getEmotion(), actualQuestion.getEmotion());
        verify(questionRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    public void findQuestionByNonexistentId() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> questionService.findById(anyLong())).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_withCorrectArguments_returnsCreatedQuestion() {
        Emotion emotion = new Emotion(1L, "test");
        Question question = generateQuestion("question", emotion);
        when(questionRepository.save(any())).thenReturn(question);
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("question", emotion);
        QuestionResponseDto questionResponseDto = questionService.create(questionRequestDto);
        assertEquals(questionRequestDto.getText(), question.getText());
        assertEquals(questionResponseDto.getEmotion(), question.getEmotion());
        assertNull(questionResponseDto.getId());
        verify(questionRepository, times(1)).save(any());
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    public void add_withIncorrectEmotionId_throwsResourceNotFoundException() {
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
        QuestionResponseDto actual = questionService.update(1L, questionRequestDto);
        assertEquals(actual.getText(), expected.getText());
        assertEquals(actual.getEmotion(), expected.getEmotion());
        verify(questionRepository, times(1)).save(any());
        verifyNoMoreInteractions(questionRepository);

    }

    @Test
    public void update_withIncorrectQuestionId_throwsResourceNotFoundException() {
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
    public void deleteById_withIncorrectQuestionId_throwsResourceNotFoundException() {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> questionService.deleteById(anyLong()))
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