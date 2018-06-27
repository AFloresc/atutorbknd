package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Page<Lesson> findBySubheaderId(Long subheaderId, Pageable pageable);
}
