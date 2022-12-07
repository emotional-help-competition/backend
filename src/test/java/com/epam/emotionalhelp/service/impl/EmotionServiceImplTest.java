package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmotionServiceImplTest {
    @Mock
    private EmotionRepository emotionRepository;

    private EmotionService emotionService;

    @BeforeEach
    void setUp() {
        emotionService = new EmotionServiceImpl(emotionRepository);
    }


    @Test
    void findAll_returnsPageEmotionResponseDto() {
        List<Emotion> emotions = generateEmotionList();
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Emotion> emotionPage = new PageImpl<>(emotions, pageable, 5);
        when(emotionRepository.findAll(pageable)).thenReturn(emotionPage);
        assertEquals(3, emotionService.findAll(pageable).getTotalPages());
        assertEquals(5, emotionService.findAll(pageable).getTotalElements());
        verify(emotionRepository, times(2)).findAll(pageable);
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    public void findById_withExistingEmotionId_returnsQuestion() {
        Emotion emotion = new Emotion("test");
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));
        EmotionResponseDto emotionResponseDto = emotionService.findById(1L);
        assertEquals(emotion.getId(), emotionResponseDto.getId());
        assertEquals(emotion.getDescription(), emotionResponseDto.getDescription());
        verify(emotionRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    public void findEmotionByNonexistentId() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> emotionService.findById(anyLong())).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_withCorrectArguments_returnsCreatedEmotion() {
        Emotion emotion = new Emotion("emotion");
        when(emotionRepository.save(any())).thenReturn(emotion);
        EmotionRequestDto emotionRequestDto = new EmotionRequestDto();
        emotionRequestDto.setDescription("emotion");
        EmotionResponseDto emotionResponseDto = emotionService.create(emotionRequestDto);
        assertEquals(emotionResponseDto.getDescription(), emotion.getDescription());
        assertNull(emotionResponseDto.getId());
        verify(emotionRepository, times(1)).save(any());
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    void updateData_withCorrectArguments_returnsUpdatedEmotion() {
        Emotion emotion = new Emotion("emotion");
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));
        Emotion expected = new Emotion("emotion1");
        when(emotionRepository.save(any())).thenReturn(expected);
        EmotionRequestDto emotionRequestDto = new EmotionRequestDto();
        emotionRequestDto.setDescription("emotion1");
        EmotionResponseDto emotionResponseDto = emotionService.update(1L, emotionRequestDto);
        assertEquals(emotionResponseDto.getDescription(), expected.getDescription());
        verify(emotionRepository, times(1)).save(any());
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    public void update_withIncorrectEmotionId_throwsResourceNotFoundException() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        EmotionRequestDto emotionRequestDto = new EmotionRequestDto();
        emotionRequestDto.setDescription("emotion1");
        assertThatThrownBy(() -> emotionService.update(1L, emotionRequestDto))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_withCorrectEmotionId() {
        Emotion emotion = new Emotion("emotion");
        when(emotionRepository.findById(1L)).thenReturn(Optional.of(emotion));
        emotionService.deleteById(1L);
        verify(emotionRepository, times(1)).delete(emotion);
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    public void deleteById_withIncorrectEmotion_throwsResourceNotFoundException() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> emotionService.deleteById(anyLong()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    private List<Emotion> generateEmotionList() {
        List<Emotion> emotions = new ArrayList<>();
        emotions.add(new Emotion("emotion"));
        emotions.add(new Emotion("emotion1"));
        emotions.add(new Emotion("emotion2"));
        emotions.add(new Emotion("emotion3"));
        emotions.add(new Emotion("emotion4"));
        return emotions;
    }
}
