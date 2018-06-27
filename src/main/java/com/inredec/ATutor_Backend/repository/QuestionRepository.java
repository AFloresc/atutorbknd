package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Lesson;
import com.inredec.ATutor_Backend.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Lesson> findByTestId(Long testId, Pageable pageable);
}
