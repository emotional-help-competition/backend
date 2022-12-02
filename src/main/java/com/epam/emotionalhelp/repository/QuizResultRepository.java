package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    Collection<QuizResult> findAllById(Long resultId);

    @Query(value = "SELECT MAX(id) + 1 FROM QuizResult")
    Long getNextRowId();
}
