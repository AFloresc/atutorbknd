package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Subheader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubheaderRepository extends JpaRepository<Subheader, Long> {
    Page<Subheader> findByTopicId(Long topicId, Pageable pageable);
}
