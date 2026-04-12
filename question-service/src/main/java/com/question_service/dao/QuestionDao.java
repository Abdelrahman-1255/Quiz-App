package com.question_service.dao;

import org.springframework.stereotype.Repository;
import com.question_service.model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomByCategory(String category, Integer numQ);

    

    
}
