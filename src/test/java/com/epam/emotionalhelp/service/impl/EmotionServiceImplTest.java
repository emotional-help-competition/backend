package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmotionServiceImplTest {

    @Mock
    private EmotionRepository emotionRepository;

    private EmotionService emotionService;

    @BeforeEach
    void setUp() {
        emotionService = new EmotionServiceImpl(emotionRepository);
    }


    @Test
    void findAll_returnsPageEmotionResponseDto() {
        var emotions = EmotionProvider.generateEmotionList();
        var pageable = PageRequest.of(0, 2);
        var emotionPage = new PageImpl<>(emotions, pageable, 5);
        when(emotionRepository.findAll(pageable)).thenReturn(emotionPage);

        var totalPages = emotionService.findAll(pageable).getTotalPages();
        var totalElements = emotionService.findAll(pageable).getTotalElements();

        assertEquals(3, totalPages);
        assertEquals(5, totalElements);

        verify(emotionRepository, times(2)).findAll(pageable);
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    void findById_withExistingEmotionId_returnsQuestion() {
        var emotion = EmotionProvider.createEmotion();
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));

        var emotionResponseDto = emotionService.findById(1L);

        var actualEmotionId = emotionResponseDto.getId();
        var actualEmotionDescription = emotionResponseDto.getDescription();

        assertEquals(emotion.getId(), actualEmotionId);
        assertEquals(emotion.getDescription(), actualEmotionDescription);

        verify(emotionRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    void findEmotionByNonexistentId() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> emotionService.findById(1L));
    }

    @Test
    void create_withCorrectArguments_returnsCreatedEmotion() {
        var emotion = EmotionProvider.createEmotion();
        when(emotionRepository.save(any())).thenReturn(emotion);

        var emotionRequestDto = EmotionProvider.createEmotionDTO();
        var emotionResponseDto = emotionService.create(emotionRequestDto);
        var actualEmotionDescription = emotion.getDescription();

        assertEquals(emotionResponseDto.getDescription(), actualEmotionDescription);
        assertNull(emotionResponseDto.getId());

        verify(emotionRepository, times(1)).save(any());
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    void updateData_withCorrectArguments_returnsUpdatedEmotion() {
        var emotion = EmotionProvider.createEmotion();
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.of(emotion));

        var expected = EmotionProvider.createAnotherEmotion();
        when(emotionRepository.save(any())).thenReturn(expected);

        var emotionRequestDto = EmotionProvider.createAnotherEmotion();

        var emotionResponseDto = emotionService.update(1L, emotionRequestDto);
        var actualEmotionDescription = expected.getDescription();

        assertEquals(emotionResponseDto.getDescription(), actualEmotionDescription);

        verify(emotionRepository, times(1)).save(any());
        verifyNoMoreInteractions(emotionRepository);
    }

    @Test
    void update_withIncorrectEmotionId_throwsResourceNotFoundException() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        var emotionRequestDto = EmotionProvider.createAnotherEmotionDTO();
        
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
     void deleteById_withIncorrectEmotion_throwsResourceNotFoundException() {
        when(emotionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> emotionService.deleteById(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }


    @UtilityClass
    private static final class EmotionProvider {

        private static final String EMOTION_VALUE = "emotion";
        
        Emotion createEmotion() {
            return new Emotion(EMOTION_VALUE);
        }

        Emotion createAnotherEmotion() {
            return new Emotion("emotion2");
        }

        EmotionRequestDto createEmotionDTO() {
            var emotionDto = new EmotionRequestDto();
            emotionDto.setDescription(EMOTION_VALUE);
            return emotionDto;
        }

        EmotionRequestDto createAnotherEmotionDTO() {
            var emotionDto = new EmotionRequestDto();
            emotionDto.setDescription("emotion1");
            return emotionDto;
        }
        
        List<Emotion> generateEmotionList() {
            return List.of(
                    new Emotion("emotion"),
                    new Emotion("emotion1"),
                    new Emotion("emotion2"),
                    new Emotion("emotion3"),
                    new Emotion("emotion4")
            );
        }
    }
}
