package com.inredec.ATutor_Backend.repository;

import com.inredec.ATutor_Backend.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRespository extends JpaRepository<Topic, Long> {
}

