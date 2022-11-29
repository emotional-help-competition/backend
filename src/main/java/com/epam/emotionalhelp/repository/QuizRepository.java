package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}