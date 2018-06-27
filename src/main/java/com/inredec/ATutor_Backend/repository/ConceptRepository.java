package com.inredec.ATutor_Backend.repository;

import com.inredec.ATutor_Backend.model.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long>{


}
