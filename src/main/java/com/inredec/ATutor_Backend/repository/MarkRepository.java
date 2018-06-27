package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Lesson;
import com.inredec.ATutor_Backend.model.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
        Page<Mark> findAllByUserIdAndTestId(Long testId, Long testid, Pageable pageable);

}

