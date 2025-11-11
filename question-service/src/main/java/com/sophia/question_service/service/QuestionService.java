package com.sophia.question_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophia.question_service.model.Question;
import com.sophia.question_service.repository.QuestionRepository;

@Service
public class QuestionService {

    private QuestionRepository repo;
    @Autowired
    public void setQuestionRepository(QuestionRepository repository){
        this.repo = repository;
    }

    public List<Question> getAllQuestions() {
        return repo.findAll();
    }

    public Question addQuestion(Question question) {
        return repo.save(question);
    }

    public Question getQuestionById(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Question> getQuestionByCategory(String category) {
        return repo.findByCategory(category);
    }

}
