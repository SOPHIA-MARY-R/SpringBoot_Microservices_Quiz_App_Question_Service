package com.sophia.question_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/")
    public String greet() {
        return "Hi Sophia!";
    }
    
}
