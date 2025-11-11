package com.sophia.question_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sophia.question_service.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    List<Question> findByCategory(String category);//as category is also a field, JPA is smart enough to fetch the data based on the category provided

    //we only need question ids
    @Query(value="SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery=true)
    List<Integer> findRandomQuestionsByCategory(int numQ, String category);
}
