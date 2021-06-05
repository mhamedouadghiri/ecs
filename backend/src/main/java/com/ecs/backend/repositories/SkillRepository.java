package com.ecs.backend.repositories;

import com.ecs.backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findAllByStudentId(Long studentId);
}
