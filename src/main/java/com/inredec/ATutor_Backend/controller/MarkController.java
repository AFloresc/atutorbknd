package com.inredec.ATutor_Backend.controller;

import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Content;
import com.inredec.ATutor_Backend.model.Lesson;
import com.inredec.ATutor_Backend.model.Mark;
import com.inredec.ATutor_Backend.repository.MarkRepository;
import com.inredec.ATutor_Backend.repository.TestRepository;
import com.inredec.ATutor_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MarkController {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;


    @GetMapping("/user/{userId}/test/{testId}/marks")
    public Page<Mark> getMarksByIds(@PathVariable(value = "userId") Long userId,
                                    @PathVariable(value = "testId") Long testId,
                                    Pageable pageable) {
        return markRepository.findAllByUserIdAndTestId(userId, testId, pageable);
    }



}
