package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(
            value =
                    "SELECT * FROM question WHERE "
                            + "(?1 IS NULL OR LOWER(text) LIKE LOWER(CONCAT('%', ?1, '%'))) ",
            nativeQuery = true)
    Page<Question> findAll(String text, Pageable pageable);
}
