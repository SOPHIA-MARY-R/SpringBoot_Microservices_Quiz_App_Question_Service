package com.sophia.question_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {//answers for each question in the question
    private int id;//question id
    private String response;//provided answer which needs to be validated against the right answer
}
