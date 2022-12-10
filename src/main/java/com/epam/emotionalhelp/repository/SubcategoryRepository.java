package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("SELECT sub FROM Subcategory sub WHERE sub.weight < :percentage AND sub.emotion.id = :id ORDER BY sub.weight DESC")
    List<Subcategory> findAllSubcategories(@Param("percentage") Integer percentage, @Param("id") Long emotionId);
}
