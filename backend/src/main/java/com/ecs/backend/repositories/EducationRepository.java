package com.ecs.backend.repositories;

import com.ecs.backend.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository  extends JpaRepository<Education, Long> {

    List<Education> findAllByStudentId(Long studentId);
}
