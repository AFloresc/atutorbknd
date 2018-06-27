package com.inredec.ATutor_Backend.repository;


import com.inredec.ATutor_Backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {
}
