package com.inredec.ATutor_Backend.controller;


import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Lesson;
import com.inredec.ATutor_Backend.repository.LessonRepository;
import com.inredec.ATutor_Backend.repository.SubheaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SubheaderRepository subheaderRepository;

    @GetMapping("/subheader/{subheaderId}/lessons")
    public Page<Lesson> getAllLessonsBySubheaderId(@PathVariable (value = "subheaderId") Long subheaderId,
                                                Pageable pageable) {
        return lessonRepository.findBySubheaderId(subheaderId, pageable);
    }

    @PostMapping("/subheader/{subheaderId}/lessons")
    public Lesson createLesson(@PathVariable (value = "subheaderId") Long subheaderId,
                                 @Valid @RequestBody Lesson lesson) {
        return subheaderRepository.findById(subheaderId).map(subheader -> {
            lesson.setSubheader(subheader);
            return lessonRepository.save(lesson);
        }).orElseThrow(() -> new ResourceNotFoundException("UbheaderId ", "not found ", subheaderId));
    }

    @PutMapping("/subheader/{subheaderId}/lessons/{lessonId}")
    public Lesson updateLesson(@PathVariable (value = "subheaderId") Long subheaderId,
                                 @PathVariable (value = "lessonId") Long lessonId,
                                 @Valid @RequestBody Lesson lessonRequest) {
        if(!subheaderRepository.existsById(subheaderId)) {
            throw new ResourceNotFoundException("SubheaderId " ,"not found", subheaderId);
        }

        return lessonRepository.findById(lessonId).map(lesson -> {
            lesson.setName(lessonRequest.getName());
            return lessonRepository.save(lesson);
        }).orElseThrow(() -> new ResourceNotFoundException("LessonId " , "not found", lessonId));
    }

    @DeleteMapping("/subheader/{subheaderId}/lessons/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable (value = "subheaderId") Long subheadertId,
                                           @PathVariable (value = "lessonId") Long lessonId) {
        if(!subheaderRepository.existsById(subheadertId)) {
            throw new ResourceNotFoundException("SubheaderId ", "not found", subheadertId);
        }

        return lessonRepository.findById(lessonId).map(lesson -> {
            lessonRepository.delete(lesson);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId ",  " not found", lessonId));
    }


}
