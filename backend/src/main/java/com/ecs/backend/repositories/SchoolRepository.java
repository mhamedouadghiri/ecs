package com.ecs.backend.repositories;

import com.ecs.backend.model.School;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

    User findByEmail(String email);
}
