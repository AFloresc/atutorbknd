package com.inredec.ATutorBackend.controller;

import com.inredec.ATutorBackend.exception.ResourceNotFoundException;
import com.inredec.ATutorBackend.model.Question;
import com.inredec.ATutorBackend.model.Test;
import com.inredec.ATutorBackend.repository.QuestionRepository;
import com.inredec.ATutorBackend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    QuestionRepository quiestionRepository;

    // Get all Questions
    @GetMapping("/quiestions")
    public List<Question> getAllQuestions(){
        return quiestionRepository.findAll();
    }

    // Get a Single Question
    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Long testId){
        return quiestionRepository.findById(testId).orElseThrow(() -> new ResourceNotFoundException("Test", "id", testId));
    }

    // Get all Questions by TestId
    @GetMapping("/questions/test/{id}")
    public Question getAllQuestionByTestId(@PathVariable(value = "id") Long testId){
        return quiestionRepository.findById(testId).orElseThrow(() -> new ResourceNotFoundException("Test", "id", testId));
    }
}
