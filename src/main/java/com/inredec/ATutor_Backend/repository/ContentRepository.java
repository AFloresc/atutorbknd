package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Page<Content> findByLessonId(Long lessonId, Pageable pageable);
}
