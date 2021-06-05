package com.ecs.backend.repositories;

import com.ecs.backend.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    List<Experience> findAllByStudentId(Long studentId);
}
