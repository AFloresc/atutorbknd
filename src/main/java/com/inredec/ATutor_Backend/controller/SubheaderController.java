package com.inredec.ATutor_Backend.controller;


import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Subheader;
import com.inredec.ATutor_Backend.repository.SubheaderRepository;
import com.inredec.ATutor_Backend.repository.TopicRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SubheaderController {

    @Autowired
    private SubheaderRepository subheaderRepository;

    @Autowired
    private TopicRespository topicRespository;

    @GetMapping("/topics/{topicId}/subheaders")
    public Page<Subheader> getAllSubheadersByTopic(@PathVariable(value = "topicId") Long topicId,
                                                  Pageable pageable) {
        return subheaderRepository.findByTopicId(topicId, pageable);
    }

    @PostMapping("/topics/{topicId}/comments/")
    public Subheader createSubheader(@PathVariable (value = "topicId") Long topicId,
                                 @Valid @RequestBody Subheader subheader) {
        return topicRespository.findById(topicId).map(topic -> {
            subheader.setTopic(topic);
            return subheaderRepository.save(subheader);
        }).orElseThrow(() -> new ResourceNotFoundException("TopicId", "id", topicId));
    }

    @PutMapping("/topics/{topicId}/comments/{subheaderId}")
    public Subheader updateSubheader(@PathVariable (value = "topicId") Long topicId,
                                 @PathVariable (value = "subheadertId") Long subheaderId,
                                 @Valid @RequestBody Subheader subheaderRequest) {
        if(!topicRespository.existsById(topicId)) {
            throw new ResourceNotFoundException("SubheaderId ", " not found", subheaderId);
        }

        return subheaderRepository.findById(subheaderId).map(subheader -> {
            subheader.setName(subheaderRequest.getName());
            return subheaderRepository.save(subheader);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " ,"not found", subheaderId));
    }


}
