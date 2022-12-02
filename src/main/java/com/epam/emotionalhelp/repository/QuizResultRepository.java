package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
}
