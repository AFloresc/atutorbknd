package com.inredec.ATutor_Backend.controller;

import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Topic;
import com.inredec.ATutor_Backend.repository.TopicRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    TopicRespository topicRepository;

    @GetMapping("/topics")
    public Page<Topic> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @PostMapping("/topics")
    public Topic createTopic(@Valid @RequestBody Topic topic) {
        return topicRepository.save(topic);
    }

    @PutMapping("/topics/{topicId}")
    public Topic updatePost(@PathVariable Long topicId, @Valid @RequestBody Topic postRequest) {
        return topicRepository.findById(topicId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setUrl_image(postRequest.getUrl_image());
            return topicRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId ",  "not found ", topicId));
    }

    @DeleteMapping("/topics/{topicId}")
    public ResponseEntity<?> deletePost(@PathVariable Long topicId) {
        return topicRepository.findById(topicId).map(post -> {
            topicRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " ,"not found", topicId));
    }


}
