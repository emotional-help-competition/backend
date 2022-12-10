package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.QuizResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    Page<QuizResult> findAll(Pageable pageable);
    
    List<QuizResult> findQuizResultByAttemptId(Long id);
}