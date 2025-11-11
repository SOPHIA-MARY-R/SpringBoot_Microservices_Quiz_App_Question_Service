package com.sophia.question_service.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

//we wrap it in a question wrapper so that we send only necessary info about questions to clitn
@Component
@Data
@NoArgsConstructor
public class QuestionWrapper {
    private int id;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    public QuestionWrapper(int id, String question, String choice1, String choice2, String choice3, String choice4) {
        this.id = id;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    
}
