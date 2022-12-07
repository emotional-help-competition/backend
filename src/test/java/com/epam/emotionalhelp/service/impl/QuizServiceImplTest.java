package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.service.QuestionService;
import com.epam.emotionalhelp.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;
    @Mock
    private QuestionRepository questionRepository;

    private QuizService quizService;

    @BeforeEach
    void setUp() {
        quizService = new QuizServiceImpl(quizRepository, questionRepository);
    }

    @Test
    void findAll_returnsPageQuizResponseDto() {
        List<Quiz> quizzes = new ArrayList<>();
        Set<Question> questions = generateQuestions();
        quizzes.add(generateQuiz("quiz description1", "quiz name1", questions));
        quizzes.add(generateQuiz("quiz description2", "quiz name2", questions));
        quizzes.add(generateQuiz("quiz description3", "quiz name3", questions));
        quizzes.add(generateQuiz("quiz description4", "quiz name4", questions));
        quizzes.add(generateQuiz("quiz description5", "quiz name5", questions));

        PageRequest pageable = PageRequest.of(0, 2);
        Page<Quiz> quizPage = new PageImpl<>(quizzes, pageable, 5);
        when(quizRepository.findAll(pageable)).thenReturn(quizPage);
        assertEquals(3, quizService.findAll(pageable).getTotalPages());
        assertEquals(5, quizService.findAll(pageable).getTotalElements());
        verify(quizRepository, times(2)).findAll(pageable);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    public void findById_withExistingQuizId_returnsQuestion() {
        Set<Question> questions = generateQuestions();
        Quiz quiz = generateQuiz("quiz description", "quiz name", questions);
        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(quiz));
        QuizResponseDto actualQuiz = quizService.findById(1L);
        assertEquals(quiz.getId(), actualQuiz.getId());
        assertEquals(quiz.getDescription(), actualQuiz.getDescription());
        assertEquals(quiz.getName(), actualQuiz.getName());
        assertEquals(quiz.getQuestions(), actualQuiz.getQuestions());
        verify(quizRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(quizRepository);
    }

    @Test
    public void findQuizByNonexistentId() {
        when(quizRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quizService.findById(anyLong())).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_withCorrectArguments_returnsCreatedQuiz() {
        Set<Question> questions = generateQuestions();
        Quiz quiz = generateQuiz("quiz description", "quiz name", questions);
        when(quizRepository.save(any())).thenReturn(quiz);
        Question[] questionArray = questions.toArray(Question[]::new);
        when(questionRepository.findById(1L)).thenReturn(Optional.ofNullable(questionArray[0]));
        when(questionRepository.findById(2L)).thenReturn(Optional.of(questionArray[1]));
        when(questionRepository.findById(3L)).thenReturn(Optional.of(questionArray[2]));
        when(questionRepository.findById(4L)).thenReturn(Optional.of(questionArray[3]));
        when(questionRepository.findById(5L)).thenReturn(Optional.of(questionArray[4]));
        QuizRequestDto quizRequestDto = new QuizRequestDto("quiz name", "quiz description", questions);
        QuizResponseDto quizResponseDto = quizService.create(quizRequestDto);
        assertEquals(quizResponseDto.getDescription(), quiz.getDescription());
        assertEquals(quizRequestDto.getName(), quiz.getName());
        assertEquals(quizRequestDto.getQuestions(), quiz.getQuestions());
        assertNull(quizResponseDto.getId());
        verify(quizRepository, times(1)).save(any());
        verifyNoMoreInteractions(quizRepository);
    }

    @Test
    public void add_withIncorrectQuestionId_throwsResourceNotFoundException() {
        Set<Question> questions = generateQuestions();
        QuizRequestDto quizRequestDto = new QuizRequestDto("quiz name", "quiz description", questions);

        when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quizService.create(quizRequestDto)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void updateData_withCorrectArguments_returnsUpdatedQuiz() {
        Set<Question> questions = generateQuestions();
        Quiz quiz = generateQuiz("quiz description", "quiz name", questions);
        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(quiz));
        Set<Question> updateQuestions = new HashSet<>();
        questions.add(new Question(6L, "question5", new Emotion("emotion5")));
        questions.add(new Question(7L, "question6", new Emotion("emotion6")));
        questions.add(new Question(8L, "question7", new Emotion("emotion7")));
        questions.add(new Question(9L, "question8", new Emotion("emotion8")));
        questions.add(new Question(10L, "question9", new Emotion("emotion9")));
        Quiz expected = generateQuiz("quiz description1", "quiz name1", updateQuestions);
        when(quizRepository.save(any())).thenReturn(expected);
        QuizRequestDto quizRequestDto = new QuizRequestDto(expected.getName(), expected.getDescription(), expected.getQuestions());
        QuizResponseDto actual = quizService.update(1L, quizRequestDto);
        assertEquals(actual.getDescription(), expected.getDescription());
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getQuestions(), expected.getQuestions());
        verify(quizRepository, times(1)).save(any());
        verifyNoMoreInteractions(quizRepository);
    }

    @Test
    public void update_withIncorrectQuizId_throwsResourceNotFoundException() {
        when(quizRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quizService.update(1L, any())).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_withCorrectQuizId() {
        Set<Question> questions = generateQuestions();
        Quiz quiz = generateQuiz("quiz description1", "quiz name1", questions);
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));
        quizService.deleteById(1L);
        verify(quizRepository, times(1)).delete(quiz);
        verifyNoMoreInteractions(quizRepository);
    }

    @Test
    public void deleteById_withIncorrectQuizId_throwsResourceNotFoundException() {
        when(quizRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> quizService.deleteById(anyLong())).isInstanceOf(ResourceNotFoundException.class);
    }


    private Quiz generateQuiz(String description, String name, Set<Question> questions) {
        Quiz quiz = new Quiz();
        quiz.setDescription(description);
        quiz.setName(name);
        quiz.setQuestions(questions);
        return quiz;
    }

    private Set<Question> generateQuestions() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question(1L, "question", new Emotion("emotion")));
        questions.add(new Question(2L, "question1", new Emotion("emotion1")));
        questions.add(new Question(3L, "question2", new Emotion("emotion2")));
        questions.add(new Question(4L, "question3", new Emotion("emotion3")));
        questions.add(new Question(5L, "question4", new Emotion("emotion4")));
        return questions;
    }
}
