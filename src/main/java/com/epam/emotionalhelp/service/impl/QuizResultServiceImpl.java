package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.AttemptDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.controller.dto.SubcategoryDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.QuizAttempt;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.repository.EmotionRepository;
import com.epam.emotionalhelp.repository.QuizAttemptRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.SubcategoryRepository;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.epam.emotionalhelp.service.impl.QuizResultServiceImpl.QuizResultUtil.calculateEmotionsPercentage;
import static com.epam.emotionalhelp.service.impl.QuizResultServiceImpl.QuizResultUtil.subcategoryMapper;


/**
 * The type Quiz result service.
 *
 * @see QuizResultServiceStreamApproach
 * @deprecated to be removed in future releases
 */
@Service
@RequiredArgsConstructor
@Deprecated(since = "1.0", forRemoval = true)
public class QuizResultServiceImpl implements QuizResultService {

    private static final int MAX_SCORE_VALUE = 5;
    private static final int PERCENTAGE_VALUE = 100;
    private static final int SUBCATEGORIES_LIMIT_VALUE = 12;
    private static final int MAX_CONTAINER_SIZE = 3;
    private static final int LIST_SLICE_SIZE = 6;

    private final QuizResultRepository quizResultRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final EmotionRepository emotionRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public AttemptDto calculate(Long quizId, List<EmotionDto> emotions) {
        // 1. Find passed Quiz
        var quiz = quizRepository.findById(quizId);

        // 2. Create QuizAttempt object
        var quizAttempt = QuizAttempt.builder().createDate(LocalDateTime.now()).build();
        var quizAttemptFinal = quizAttemptRepository.save(quizAttempt);

        // 3. Filter the list to divide data by Emotions
        var emotionsMap = filterByEmotions(emotions);
        var emotionPercentages = calculateEmotionPercentage(emotionsMap);

        // 4. Get the set of emotions
        emotionPercentages.forEach((emotion, score) -> {
            var quizResult = QuizResult.builder()
                    .attempt(quizAttemptFinal)
                    .emotion(emotion)
                    .score(score)
                    .build();
            quiz.ifPresent(quizResult::setQuiz);
            quizResultRepository.save(quizResult);
        });
        return new AttemptDto(quizAttempt.getId());
    }

    @Override
    public List<EmotionalMapDto> findQuizResultsByAttemptId(Long attemptId) {
        var resultList = new ArrayList<EmotionalMapDto>();

        // 1. Find QuizResult(Emotion, Overall score)
        var quizResults = quizResultRepository.findAllByAttemptId(attemptId);

        // 2. Extract emotion and get subcategories based on '%'
        quizResults.forEach(quizResult -> {
            var quizResultEmotionDto = new EmotionalMapDto();
            var emotion = quizResult.getEmotion();
            quizResultEmotionDto.setCategory(emotion.getDescription());

            // find all subcategories
            // if score is equal to 0 it means that list with subcategories should be empty
            if (quizResult.getScore() == 0) {
                quizResultEmotionDto.setSubCategories(new ArrayList<>());
            } else {
                var categoryList = initSubcategoryList(quizResult, emotion.getId());
                quizResultEmotionDto.setSubCategories(categoryList);
            }
            resultList.add(quizResultEmotionDto);
        });
        return resultList;
    }

    private List<SubcategoryDto> initSubcategoryList(QuizResult quizResult, Long emotionId) {
        final int percentage = (int) (((double) quizResult.getScore() / MAX_SCORE_VALUE) * PERCENTAGE_VALUE);
        var allSubcategories = subcategoryRepository.findAllSubcategories(percentage, emotionId);
        var subcategories = allSubcategories
                .stream()
                .limit(SUBCATEGORIES_LIMIT_VALUE)
                .sorted(Comparator.comparing(Subcategory::getWeight))
                .collect(Collectors.toList());
        var data = ListUtils.partition(subcategories, MAX_CONTAINER_SIZE);
        return data.stream().map(subcategoryMapper()).collect(Collectors.toCollection(ArrayList::new));
    }

    private Map<Emotion, List<EmotionDto>> filterByEmotions(final List<EmotionDto> inputEmotions) {
        // 1. Fill map with EMOTIONS and its SUBCATEGORIES
        final Map<Emotion, List<EmotionDto>> result = new HashMap<>();

        // 2. Limit input emotions by 6 maximum
        var emotionsList = inputEmotions.size() > LIST_SLICE_SIZE ? new ArrayList<>(inputEmotions.subList(0, LIST_SLICE_SIZE)) : inputEmotions;
        emotionsList.forEach(emotionDto -> {
            // find emotion
            var emotion = emotionRepository.findById(emotionDto.getEmotionId()).orElse(new Emotion());
            // check if map already contains EMOTION
            if (result.containsKey(emotion)) {
                result.get(emotion).add(emotionDto);
            } else {
                var data = new ArrayList<EmotionDto>();
                data.add(emotionDto);
                result.put(emotion, data);
            }
        });

        // 3. Fill map with EMOTIONS which are not included in the input list
        final List<Emotion> emotions = emotionRepository.findAll();
        final int maxSize = LIST_SLICE_SIZE - result.values().size();
        IntStream.range(0, maxSize).forEachOrdered(i -> emotions.stream()
                .filter(emotionCategory -> !result.containsKey(emotionCategory))
                .findFirst()
                .ifPresent(emotionCategory -> result.put(emotionCategory, new ArrayList<>())));
        return result;
    }

    private Map<Emotion, Integer> calculateEmotionPercentage(Map<Emotion, List<EmotionDto>> data) {
        final Map<Emotion, Integer> result = new HashMap<>();
        data.forEach((category, emotions) -> {
            var emotion = emotionRepository.findEmotionByDescription(category.getDescription());
            var percentage = emotions.isEmpty() ? 0 : calculateEmotionsPercentage(emotions);
            result.put(emotion, percentage);
        });
        return result;
    }

    @UtilityClass
    static final class QuizResultUtil {

        public static int calculateEmotionsPercentage(List<EmotionDto> emotions) {
            final int sum = emotions.stream().mapToInt(EmotionDto::getValue).sum();
            return sum / emotions.size();
        }

        public static Function<List<Subcategory>, SubcategoryDto> subcategoryMapper() {
            return subcategory -> new SubcategoryDto(
                    extractCategories(subcategory),
                    extractScore(subcategory)
            );
        }

        private static Set<String> extractCategories(List<Subcategory> partition) {
            return partition.stream().map(Subcategory::getDescription).collect(Collectors.toSet());
        }

        private static double extractScore(List<Subcategory> partition) {
            final int maxWeight = partition.stream().mapToInt(Subcategory::getWeight).max().orElse(0);
            return (double) maxWeight / PERCENTAGE_VALUE;
        }
    }
}