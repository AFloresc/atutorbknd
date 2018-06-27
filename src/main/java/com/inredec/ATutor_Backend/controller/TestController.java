package com.inredec.ATutor_Backend.controller;


import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Test;
import com.inredec.ATutor_Backend.model.Topic;
import com.inredec.ATutor_Backend.repository.TestRepository;
import com.inredec.ATutor_Backend.repository.TopicRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/tests")
    public Page<Test> getAllTests(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    @PostMapping("/tests")
    public Test createTest(@Valid @RequestBody Test test) {
        return testRepository.save(test);
    }

    @PutMapping("/test/{testId}")
    public Test updateTest(@PathVariable Long testId, @Valid @RequestBody Test testRequest) {
        return testRepository.findById(testId).map(test -> {
            test.setDescription(testRequest.getDescription());
            return testRepository.save(test);
        }).orElseThrow(() -> new ResourceNotFoundException("TestId ",  "not found ", testId));
    }

    @DeleteMapping("/test/{testId}")
    public ResponseEntity<?> deletePost(@PathVariable Long testId) {
        return testRepository.findById(testId).map(post -> {
            testRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " ,"not found", testId));
    }

}
