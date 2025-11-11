package com.sophia.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sophia.question_service.model.Question;
import com.sophia.question_service.model.QuestionWrapper;
import com.sophia.question_service.model.Response;
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

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQ) {
        return new ResponseEntity<>(repo.findRandomQuestionsByCategory(numQ, category), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();

        for(Integer id : questionIds){
            Optional<Question> question = repo.findById(id);
            questionsForUsers.add(new QuestionWrapper(id,
                question.get().getQuestion(),//we use .get() because question is of type Optional
                question.get().getChoice1(),
                question.get().getChoice2(),
                question.get().getChoice3(),
                question.get().getChoice4()));
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for(Response response : responses){
            Optional<Question> question = repo.findById(response.getId());
            String providedAnswer = response.getResponse();
            String expectedAnswer = question.get().getAnswer();

            if(providedAnswer.equals(expectedAnswer)){
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.ACCEPTED);
    }

}
