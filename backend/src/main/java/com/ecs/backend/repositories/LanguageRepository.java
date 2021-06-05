package com.ecs.backend.repositories;

import com.ecs.backend.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    List<Language> findAllByStudentId(Long studentId);
}
