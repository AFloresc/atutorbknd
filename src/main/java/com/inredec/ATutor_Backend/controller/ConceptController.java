package com.inredec.ATutor_Backend.controller;

import com.inredec.ATutor_Backend.exception.ResourceNotFoundException;
import com.inredec.ATutor_Backend.model.Concept;
import com.inredec.ATutor_Backend.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConceptController {

    @Autowired
    ConceptRepository conceptRepository;

    // Get all concepts
    @GetMapping("/concepts")
    public List<Concept> getAllCondepts() {
        return conceptRepository.findAll();
    }

    // Create a new Concept
    @PostMapping("/concepts")
    public Concept createConcept(@Valid @RequestBody Concept concept) {
        return conceptRepository.save(concept);
    }

    // Get a single concepts
    @GetMapping("/concepts/{id}")
    public Concept getConceptById(@PathVariable(value = "id") Long conceptId) {
        return conceptRepository.findById(conceptId)
                .orElseThrow(() -> new ResourceNotFoundException("Concept", "id", conceptId));
    }

    // Update a concept
    @PutMapping("/concepts/{id}")
    public Concept updateNote(@PathVariable(value = "id") Long conceptId,
                              @Valid @RequestBody Concept conceptDetails) {

        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", conceptId));

        concept.setTitle(conceptDetails.getTitle());
        concept.setDescription(conceptDetails.getDescription());

        Concept updatedConcept = conceptRepository.save(concept);
        return updatedConcept;
    }
    // Delete a Concept
    @DeleteMapping("/concepts/{id}")
    public ResponseEntity<?> deleteConcept(@PathVariable(value = "id") Long conceptId) {
        Concept note = conceptRepository.findById(conceptId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", conceptId));

        conceptRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
