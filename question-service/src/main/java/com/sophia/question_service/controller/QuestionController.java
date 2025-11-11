package com.sophia.question_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sophia.question_service.model.Question;
import com.sophia.question_service.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/question")
public class QuestionController {

    //Setter Injection
    private QuestionService service;
    @Autowired
    public void setQuestionService(QuestionService service){
        this.service = service;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(service.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
        Question question = service.getQuestionById(id);

        if(question == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {       
        try {
            return new ResponseEntity<>(service.addQuestion(question), HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        List<Question> questions = service.getQuestionByCategory(category);

        if(questions == null || questions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }
    
}
