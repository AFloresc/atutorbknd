package com.inredec.ATutor_Backend.controller;


import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Content;
import com.inredec.ATutor_Backend.repository.ContentRepository;
import com.inredec.ATutor_Backend.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lessons/{lessonId}/contents")
    public Page<Content> getContentsByLessonId(@PathVariable(value = "lessonId") Long lessonId,
                                                    Pageable pageable) {
        return contentRepository.findByLessonId(lessonId, pageable);
    }

    @PostMapping("/lessons/{lessonId}/contents")
    public Content createContent(@PathVariable (value = "lessonId") Long lessonId,
                                 @Valid @RequestBody Content content) {
        return lessonRepository.findById(lessonId).map(lesson -> {
            content.setLesson(lesson);
            return contentRepository.save(content);
        }).orElseThrow(() -> new ResourceNotFoundException("LessonId " , "not found", lessonId));
    }

    @PutMapping("/lessons/{lessonId}/contents/{contentId}")
    public Content updateContent(@PathVariable (value = "lessonId") Long lessonId,
                                 @PathVariable (value = "contentId") Long contentId,
                                 @Valid @RequestBody Content contentRequest) {
        if(!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException("LessonId " , "not found", lessonId);
        }
        return contentRepository.findById(contentId).map(content -> {
            content.setText(contentRequest.getText());
            return contentRepository.save(content);
        }).orElseThrow(() -> new ResourceNotFoundException("ContentId ", "not found", contentId));
    }

    @DeleteMapping("/lessons/{lessonId}/concepts/{conceptId}")
    public ResponseEntity<?> deleteContent(@PathVariable (value = "lessonId") Long lessonId,
                                           @PathVariable (value = "conceptId") Long conceptId) {
        if(!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException("lessonId ", " not found", lessonId);
        }

        return contentRepository.findById(conceptId).map(content -> {
            contentRepository.delete(content);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ContentId ", "not found", conceptId));
    }
}
