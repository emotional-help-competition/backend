package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.module.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
