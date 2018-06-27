package com.inredec.ATutor_Backend.controller;

import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Lesson;
import com.inredec.ATutor_Backend.model.Question;
import com.inredec.ATutor_Backend.repository.QuestionRepository;
import com.inredec.ATutor_Backend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuestionController  {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test/{testId}/questions")
    public Page<Lesson> getQuestionsByTestId(@PathVariable(value = "testId") Long testId,
                                                   Pageable pageable) {
        return questionRepository.findByTestId(testId, pageable);
    }

    @PostMapping("/test/{testId}/questions")
    public Question createQuestion(@PathVariable (value = "testId") Long testId,
                               @Valid @RequestBody Question question) {
        return testRepository.findById(testId).map(test -> {
            question.setTest(test);
            return questionRepository.save(question);
        }).orElseThrow(() -> new ResourceNotFoundException("testId ", "not found ", testId));
    }

    @PutMapping("/test/{testId}/questions/{questionId}")
    public Question updateQuestion(@PathVariable (value = "testId") Long testId,
                               @PathVariable (value = "questionId") Long questionId,
                               @Valid @RequestBody Question questionRequest) {
        if(!testRepository.existsById(testId)) {
            throw new ResourceNotFoundException("testId " ,"not found", testId);
        }

        return questionRepository.findById(questionId).map(lesson -> {
            lesson.setAnswer1(questionRequest.getAnswer1());
            lesson.setAnswer2(questionRequest.getAnswer2());
            lesson.setAnswer3(questionRequest.getAnswer3());
            lesson.setRight_answer(questionRequest.getRight_answer());
            lesson.setHeather(questionRequest.getHeather());
            return questionRepository.save(lesson);
        }).orElseThrow(() -> new ResourceNotFoundException("QuestionId " , " not found ", questionId));
    }

    @DeleteMapping("/test/{testId}/questions/{questionId}")
    public ResponseEntity<?> deleteLesson(@PathVariable (value = "testId") Long testId,
                                          @PathVariable (value = "lessonId") Long lessonId) {
        if(!testRepository.existsById(testId)) {
            throw new ResourceNotFoundException("TestId ", "not found", testId);
        }

        return questionRepository.findById(lessonId).map(question -> {
            questionRepository.delete(question);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId ",  " not found", lessonId));
    }
}
